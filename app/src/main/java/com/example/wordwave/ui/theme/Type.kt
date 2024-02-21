package com.example.wordwave.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wordwave.R

// Set of Material typography styles to start with
val WordWaveTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
     labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 60.sp,
        letterSpacing = 0.1.sp
    )
)