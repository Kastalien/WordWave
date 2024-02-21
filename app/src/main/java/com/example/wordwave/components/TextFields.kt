package com.example.wordwave.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.wordwave.R
import com.example.wordwave.ui.theme.WordWaveTypography
import com.example.wordwave.ui.theme.textFieldColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditField(fieldValue:String, onFieldValueChange :  ((String) -> Unit)) {
    OutlinedTextField(
        value = fieldValue,
        onValueChange =   onFieldValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = textFieldColor),
        placeholder = {
            Text("login", style = WordWaveTypography.bodyMedium, color = Color.Gray)
        },
        trailingIcon = {
            Box(modifier = Modifier.width(60.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.outline_login_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        },
        textStyle = WordWaveTypography.bodyMedium,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth(0.9F)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPasswordField(fieldValue:String, onFieldValueChange :  ((String) -> Unit)) {
    OutlinedTextField(
        value = fieldValue,
        onValueChange =   onFieldValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = textFieldColor),
        placeholder = {
            Text("password", style = WordWaveTypography.bodyMedium, color = Color.Gray)
        },
        trailingIcon = {
            Box(modifier = Modifier.width(60.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_lock_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textStyle = WordWaveTypography.bodyMedium,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth(0.9F)
    )
}
