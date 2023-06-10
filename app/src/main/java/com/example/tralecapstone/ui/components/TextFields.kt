package com.example.tralecapstone.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow

@Composable
fun TextFields(
    value: String,
    onValueChange: (String) -> Unit,
    onVisibilityChange: (Boolean) -> Unit = {},
    label: String,
    color: Color,
    leadingIconImageVector: ImageVector?,
    leadingIconDescription: String = "",
    isPassField: Boolean = false,
    isPassVisible: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
    errorMessage: String = "",
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Alignment.Center
    ) {
        OutlinedTextField(
            colors =
            if (showError) TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Red)
            else TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = color),
            value = value,
            onValueChange = { onValueChange(it) },
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = Color.White
//        ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 4.dp, end = 10.dp, start = 10.dp),
            label = { Text(text = label) },
            leadingIcon = {
                if (leadingIconImageVector != null) {
                    Icon(
                        imageVector = leadingIconImageVector,
                        contentDescription = leadingIconDescription,
                        tint = if (showError) Color.Red else Yellow
                    )
                } else { }
            },
            trailingIcon = {
                if (showError && !isPassField) Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error",
                    tint = Color.Red
                )
                if (isPassField) {
                    IconButton(onClick = { onVisibilityChange(!isPassVisible) }) {
                        Icon(
                            imageVector = if (isPassVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                }
            },
            visualTransformation = when {
                isPassField && isPassVisible -> VisualTransformation.None
                isPassField -> PasswordVisualTransformation()
                else -> VisualTransformation.None
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = true
        )
        if (showError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 10.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldsPreview() {
    TraleCapstoneTheme {
        TextFields(
            value = "",
            onValueChange = {},
            label = "input pass",
            color = Yellow,
            showError = true,
            errorMessage = "Password Invalid",
            leadingIconImageVector = Icons.Default.Lock
        )
    }
}