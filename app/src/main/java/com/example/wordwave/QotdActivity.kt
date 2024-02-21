package com.example.wordwave

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordwave.components.BackButton
import com.example.wordwave.components.QuoteButton
import com.example.wordwave.components.QuoteLabel
import com.example.wordwave.ui.theme.WordWaveTheme
import com.example.wordwave.ui.theme.gradientEndColor
import com.example.wordwave.ui.theme.gradientStartColor
import com.example.wordwave.viewmodels.QotdViewModel

class QotdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordWaveTheme {
                Qofd()
            }
        }
    }
}

@Composable
fun Qofd(QotdViewModel: QotdViewModel = viewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gradientStartColor,
                        gradientEndColor
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
                .padding(top = 15.dp)
                .weight(0.1F),
            horizontalArrangement = Arrangement.Start
        )
        {
            BackButton {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        }
        Spacer(modifier = Modifier.weight(0.05F))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8F),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                border = BorderStroke(2.dp, Color.Black),
            ) {
                QuoteLabel(
                    quoteText = QotdViewModel.currentQuote.body ?: "Loading...",
                    quoteAuthor = QotdViewModel.currentQuote.author ?: ""
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3F),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            QuoteButton(
                onButtonClick = {
                    QotdViewModel.getQotd()
                },
                stringResource(R.string.quote_of_the_day)
            )
        }
        DisposableEffect(Unit) {
            QotdViewModel.getQotd()
            onDispose {}
        }
    }
}
