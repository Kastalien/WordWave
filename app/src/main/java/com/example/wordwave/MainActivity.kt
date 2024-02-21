package com.example.wordwave

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wordwave.components.BackButton
import com.example.wordwave.ui.theme.WordWaveTheme
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.ui.theme.gradientEndColor
import com.example.wordwave.ui.theme.gradientStartColor
import com.example.wordwave.ui.theme.mainButtonColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordWaveTheme {
                MainMenu()
            }
        }
    }
}

@Composable
fun MainMenu() {
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
                .weight(0.15F),
            horizontalArrangement = Arrangement.Start
        )
        {
            BackButton {
                val intent = Intent(context, AuthActivity::class.java)
                context.startActivity(intent)
            }
        }
        Spacer(modifier = Modifier.weight(0.05F))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4F),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    val intent = Intent(context, UserActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.05F))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp)
                .weight(0.2F),
            horizontalArrangement = Arrangement.End
        )
        {
            Button(
                onClick = {
                    val intent = Intent(context, QotdActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = mainButtonColor),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(0.6F)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.quote),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.quote_of_the_day),
                    style = WordWaveTypography.bodyMedium,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.02F))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
                .weight(0.2F),
            horizontalArrangement = Arrangement.Start
        )
        {
            Button(
                onClick = {
                    val intent = Intent(context, QuotesActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = mainButtonColor),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(0.6F)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.list),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Random Quotas",
                    style = WordWaveTypography.bodyMedium,
                    color = Color.Black
                )

            }
        }
        Spacer(modifier = Modifier.weight(0.1F))
    }
}

