package com.example.tralecapstone.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun FilledButton(
    text : String,
    size : Int = 40,
    color : Color,
    enable : Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(color),
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        enabled = enable,
        modifier = modifier
            .fillMaxWidth()
            .height(size.dp)
    ) {
            Text(
                text = text,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

    }

@Preview(showBackground = true)
@Composable
fun FilledButtonPreview() {
    TraleCapstoneTheme {
        FilledButton(
            text = "Book This Trip",
            color = MaterialTheme.colors.primary,
            onClick = {},
            enable = true
        )
    }
}