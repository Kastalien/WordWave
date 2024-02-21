package com.example.wordwave

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wordwave.components.BackButton
import com.example.wordwave.components.EditField
import com.example.wordwave.components.EditPasswordField
import com.example.wordwave.components.QuoteButton
import com.example.wordwave.data.Credentials
import com.example.wordwave.data.favQuotes
import com.example.wordwave.ui.theme.WordWaveTheme
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.ui.theme.gradientEndColor
import com.example.wordwave.ui.theme.gradientStartColor
import com.example.wordwave.viewmodels.QuotesViewModel

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordWaveTheme {
                UserData()
            }
        }
    }
}

@Composable
fun UserData() {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmptyAttempt by remember { mutableStateOf(false) }
    var favCount by remember { mutableStateOf(-1) }
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 30.dp)
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        )
        {
            BackButton {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp, 120.dp)
                    //modifier = Modifier
                    //    .fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp, 80.dp)
                    )
                    Card(
                        modifier = Modifier
                            .size(140.dp, 80.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(if (favCount==-1) ".." else favCount.toString(), style = WordWaveTypography.titleLarge)
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.like_hand),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp, 80.dp)
                    )
                    Card(
                        modifier = Modifier.size(140.dp, 80.dp),
                        border = BorderStroke(2.dp, Color.Black),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(favQuotes.likeCount.toString(), style = WordWaveTypography.titleLarge)
                        }
                    }
                }
            }
        }
        if (isEmptyAttempt) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isEmptyAttempt) "Please enter login and password" else "Wrong login or password!",
                style = WordWaveTypography.bodyMedium,
                color = Color.Red
            )
        }
        EditField(login) { login = it }
        Spacer(modifier = Modifier.height(20.dp))
        EditPasswordField(password) { password = it }
        Spacer(modifier = Modifier.height(20.dp))
        QuoteButton(
            onButtonClick = {
                if ((login != "") && (password != "")) {
                    Credentials.login=login
                    Credentials.password=password
                    Credentials.save(context)
                } else
                    isEmptyAttempt = true
            },
            buttonText = stringResource(R.string.change_login_and_password)
        )
        Spacer(modifier = Modifier.height(20.dp))
        QuoteButton(
            onButtonClick = {
                val intent = Intent(context, QuotesActivity::class.java)
                intent.putExtra("isFav", true)
                context.startActivity(intent)
            },
            buttonText = stringResource(R.string.favorite_button)
        )
    }
    LaunchedEffect(Unit) {
        Log.d("quote","Load from server favs count...")
        favCount=favQuotes.getAllQuotas().count()
    }
}