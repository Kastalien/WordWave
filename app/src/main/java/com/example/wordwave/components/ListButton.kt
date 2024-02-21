package com.example.wordwave.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wordwave.R

@Composable
fun ListButton(image:Painter, onButtonClick:(Int)->Unit, id:Int) {
    IconButton(
        onClick = {onButtonClick(id)},
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier.size(40.dp, 40.dp)
        )
    }
}
