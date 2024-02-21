package com.example.wordwave.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wordwave.R
import com.example.wordwave.data.Quote
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.viewmodels.QuotesViewModel

@Composable
fun QuotesList(quotes: MutableList<Quote>, startNumeration:Int, onClickFav:(Int)->Unit={}, onClickLike:(Int)->Unit={}, showButtons:Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        border = BorderStroke(2.dp, Color.Black),
    ) {
        if (quotes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(100.dp))
            }
        }
        else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(quotes) { index, quote ->
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(0.11F)
                                .fillMaxHeight()
                                .padding(top = 10.dp)
                                .padding(start = 3.dp)
                        ) {
                            Text(text = "${startNumeration+index}.", style = WordWaveTypography.bodyMedium,)
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.9F)
                                .fillMaxHeight()
                        ) {
                            QuoteLabel(quote.body ?: "", quote.author ?: "")
                        }
                        if (showButtons) {
                            Box(
                                modifier = Modifier
                                    .weight(0.1F)
                                    .fillMaxHeight()
                                    .padding(top = 10.dp)
                            ) {
                                ListButton(
                                    painterResource(id = if (quote.isFav == true) R.drawable.favorite_chosen else R.drawable.favorite),
                                    onClickFav,
                                    quote.id ?: 0
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(0.1F)
                                    .fillMaxHeight()
                                    .padding(top = 10.dp)
                            ) {
                                ListButton(
                                    painterResource(id = if (quote.isLiked == true) R.drawable.like_hand_chosen else R.drawable.like_hand),
                                    onClickLike,
                                    quote.id ?: 0
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(0.03F))
                    }
                }
            }
        }
    }
}