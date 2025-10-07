package com.example.fakestoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.fakestoreapp.Screens.ProductDetailScreen
import com.example.fakestoreapp.Screens.ProductsScreen
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme
import com.example.fakestoreapp.ui.theme.ProductDetailScreenRoute
import com.example.fakestoreapp.ui.theme.ProductScreenRoute


/*
* 1. crear modelos
* 2. crear servicios
* 3. consumir servicios
* 4. refrescar el ui
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ProductScreenRoute
                    ){
                        composable <ProductScreenRoute>{
                            ProductsScreen(
                                navController
                            )
                        }
                        composable(
                            route = "productDetail/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: 0
                            ProductDetailScreen(id)
                        }

                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FakeStoreAppTheme {

    }
}