package com.example.fakestoreapp.Screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fakestoreapp.Components.ProductCard
import com.example.fakestoreapp.Models.Product
import com.example.fakestoreapp.Services.ProductService
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen() {

    var products by remember { mutableStateOf(listOf<Product>()) }
    var loading by remember { mutableStateOf(true) }

    // Llamada a la API
    LaunchedEffect(true) {
        try {
            Log.i("HomeScreen", "Inicializando Retrofit")
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ProductService::class.java)
            val result = async(Dispatchers.IO) { service.getAllProducts() }
            products = result.await()
            loading = false
        } catch (e: Exception) {
            loading = false
            Log.e("HomeScreen", e.toString())
        }
    }

    // Vista de carga
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Lista de productos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product,
                    onClick = {
                        Log.i("HomeScreen", "Producto clickeado: ${product.title}")
            })}
        }
    }
}



@Preview
@Composable
fun HomeScreenView(){
    FakeStoreAppTheme {
        HomeScreen()
    }
}