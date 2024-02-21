package com.example.wordwave.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wordwave.data.Quote
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.viewmodels.QotdViewModel

@Composable
fun QuoteLabel(quoteText: String, quoteAuthor:String) {
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Text(
            text= quoteText,
            style = WordWaveTypography.bodyMedium,
            textAlign = TextAlign.Justify
        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text=quoteAuthor,
                style = WordWaveTypography.labelMedium
            )
        }
    }
}