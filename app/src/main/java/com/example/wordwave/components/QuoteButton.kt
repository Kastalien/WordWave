package com.example.wordwave.components

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wordwave.MainActivity
import com.example.wordwave.R
import com.example.wordwave.data.Credentials
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.ui.theme.mainButtonColor

@Composable
fun QuoteButton(onButtonClick: () -> Unit, buttonText:String) {
    Button(
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(containerColor = mainButtonColor),
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(0.9F)
    ) {
        Text(
            text = buttonText,
            style = WordWaveTypography.bodyMedium,
            color = Color.Black
        )
    }

}