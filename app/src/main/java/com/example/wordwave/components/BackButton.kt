package com.example.wordwave.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wordwave.R
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.ui.theme.backButtonColor
import com.example.wordwave.ui.theme.mainButtonColor

@Composable
fun BackButton(onButtonClick :  (() -> Unit)) {
    Button(
    onClick = onButtonClick,
    colors = ButtonDefaults.buttonColors(containerColor = backButtonColor),
    shape = RoundedCornerShape(30),
    modifier = Modifier
        .size(100.dp, 50.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.resource_return),
            contentDescription = ""
        )
    }
}