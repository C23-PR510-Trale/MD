package com.example.tralecapstone.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.ui.theme.Poppins
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun OutlinedButton(
    text : String,
    enable : Boolean,
    color : Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        border = BorderStroke(2.dp, color),
        onClick = onClick,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(14.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
            Text(
                text = text,
                color = DarkGray,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

    }

@Preview(showBackground = true)
@Composable
fun OutlinedButtonPreview() {
    TraleCapstoneTheme {
        OutlinedButton(
            text = "Book This Trip",
            color = MaterialTheme.colors.primary,
            onClick = {},
            enable = true
        )
    }
}