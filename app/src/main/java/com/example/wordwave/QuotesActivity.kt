package com.example.wordwave

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordwave.components.BackButton
import com.example.wordwave.components.QuoteButton
import com.example.wordwave.components.QuotesList
import com.example.wordwave.ui.theme.WordWaveTheme
import com.example.wordwave.ui.theme.gradientEndColor
import com.example.wordwave.ui.theme.gradientStartColor
import com.example.wordwave.viewmodels.QuotesViewModel

class QuotesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isFav=intent.getBooleanExtra("isFav", false)
        setContent {
            WordWaveTheme {
                Quotes(isFav=isFav)
            }
        }
    }
}

@Composable
fun Quotes(QuotesViewModel: QuotesViewModel = viewModel(), isFav:Boolean) {
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
                if (isFav) {
                    val intent = Intent(context, UserActivity::class.java)
                    context.startActivity(intent)
                }
                else{
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8F),
            horizontalArrangement = Arrangement.Center
        ) {
            QuotesList(quotes = QuotesViewModel.getCurrentPage(), QuotesViewModel.getStartNumeration(), QuotesViewModel::makeFav, QuotesViewModel::like, !isFav)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.12F)
                .padding(start=15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5F)
            ) {
                if (QuotesViewModel.currentPage!=0){
                QuoteButton(
                    onButtonClick = {
                        QuotesViewModel.currentPage-=1
                    },
                    stringResource(R.string.back_button)
                )
            }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5F)
            ) {
                if (QuotesViewModel.currentPage!=QuotesViewModel.pageCount-1) {
                    QuoteButton(
                        onButtonClick = {
                            QuotesViewModel.currentPage += 1
                        },
                        stringResource(R.string.next_button)
                    )
                }
            }
        }
        DisposableEffect(Unit) {
            Log.d("quote","Load from server")
            QuotesViewModel.getQuotes(isFav)
            onDispose {}
        }
    }
}