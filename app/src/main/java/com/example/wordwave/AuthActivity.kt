package com.example.wordwave

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.wordwave.components.EditField
import com.example.wordwave.components.EditPasswordField
import com.example.wordwave.components.QuoteButton
import com.example.wordwave.data.Credentials
import com.example.wordwave.data.Credentials.authFavQs
import com.example.wordwave.ui.theme.WordWaveTheme
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.ui.theme.gradientEndColor
import com.example.wordwave.ui.theme.gradientStartColor

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordWaveTheme {
                Auth()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth() {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isWrongAttempt by remember { mutableStateOf(false) }
    var isEmptyAttempt by remember { mutableStateOf(false) }
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
        Image(
            painter = painterResource(id = R.drawable.wave),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.project_title),
            style = WordWaveTypography.titleLarge
        )
        if ((isWrongAttempt) || (isEmptyAttempt)) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isEmptyAttempt) "Please enter login and password" else "Wrong login or password!",
                style = WordWaveTypography.bodyMedium,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        EditField(login) { login = it }
        Spacer(modifier = Modifier.height(20.dp))
        EditPasswordField(password) { password = it }
        Spacer(modifier = Modifier.height(20.dp))
        QuoteButton(onButtonClick = {
            if ((login != "") && (password != "")) {
                var checkResult = Credentials.check(context, login, password)
                if (checkResult) {
                    authFavQs()
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                } else {
                    isWrongAttempt = true
                }
            } else
                isEmptyAttempt = true
        },
            buttonText = stringResource(R.string.sing_in_sing_up))
    }
}