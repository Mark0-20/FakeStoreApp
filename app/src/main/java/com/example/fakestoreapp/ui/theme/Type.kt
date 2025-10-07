package com.example.fakestoreapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fakestoreapp.R

val CoffeeFont = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val CoffeeTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = CoffeeFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = CoffeeText
    ),
    titleMedium = TextStyle(
        fontFamily = CoffeeFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = CoffeeText
    ),
    bodyMedium = TextStyle(
        fontFamily = CoffeeFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = CoffeeSubtitle
    ),
    labelSmall = TextStyle(
        fontFamily = CoffeeFont,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        color = CoffeeAccent
    )
)