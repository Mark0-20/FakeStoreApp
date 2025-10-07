package com.example.fakestoreapp.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.fakestoreapp.Models.Product
import com.example.fakestoreapp.Services.ProductService
import com.example.fakestoreapp.ui.theme.CoffeeAccent
import com.example.fakestoreapp.ui.theme.CoffeeSubtitle
import com.example.fakestoreapp.ui.theme.CoffeeTypography
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Composable
fun ProductDetailScreen(id : Int){
    var product by remember { mutableStateOf<Product?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ProductService::class.java)
            val result = withContext(Dispatchers.IO) {
                service.getProductById(id)
            }

            product = result
            loading = false
            Log.i("ProductDetailScreen", product.toString())
        } catch (e: Exception) {
            loading = false
            Log.e("ProductDetailScreen", e.toString())
        }
    }

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = CoffeeAccent)
        }
    } else {
        product?.let { p ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    shape = CardDefaults.shape,
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(p.image),
                        contentDescription = p.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = p.title,
                    style = CoffeeTypography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = p.category.uppercase(),
                    style = CoffeeTypography.labelSmall.copy(
                        color = CoffeeAccent,
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = p.description,
                    style = CoffeeTypography.bodyMedium,
                    color = CoffeeSubtitle,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "$${p.price}",
                    style = CoffeeTypography.titleMedium.copy(
                        color = CoffeeAccent,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Error al cargar producto",
                style = CoffeeTypography.bodyMedium
            )
        }
    }
}