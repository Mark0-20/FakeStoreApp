package com.example.fakestoreapp.Screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fakestoreapp.Models.Product
import com.example.fakestoreapp.Services.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Composable
fun ProductDetailScreen(id : Int){

    var product by remember {
        mutableStateOf<Product?>(null)
    }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ProductService::class.java)
            val result = withContext(Dispatchers.IO) {
                service.getProductById(id)
            }
            product = result
            Log.i("ProductDetailScreen", product.toString())
        }
        catch (e: Exception)
        {
            Log.e("ProductDetailScreen", e.toString())
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(product?.title ?: "Error al cargar")
    }
}