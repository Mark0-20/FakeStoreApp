package com.example.fakestoreapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = CoffeeBrown,
    onPrimary = CoffeeCream,
    secondary = CoffeeAccent,
    onSecondary = CoffeeLight,
    background = CoffeeLight,
    surface = CoffeeCream,
    onSurface = CoffeeText
)

private val DarkColors = darkColorScheme(
    primary = CoffeeAccent,
    onPrimary = CoffeeDark,
    secondary = CoffeeBrown,
    onSecondary = CoffeeLight,
    background = CoffeeDark,
    surface = CoffeeBrown,
    onSurface = CoffeeCream
)

@Composable
fun FakeStoreAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = CoffeeTypography,
        content = content
    )
}