package com.example.tralecapstone.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun DropDownDestination(
    text: String,
    modifier: Modifier = Modifier
): String {

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var item by remember { mutableStateOf(text) }

    Box(
        modifier = modifier
            .height(40.dp)
            .background(Color.White)
            .clickable {
                expanded = !expanded
            }
    ) {

        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 20.dp)
        )

        Icon(
            imageVector = Icons.Rounded.ArrowDropDown,
            contentDescription = "More",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 20.dp)
                .size(30.dp)
        )

            DropdownMenu(
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    content = { Text("Yogyakarta") },
                    onClick = {
                        expanded = false
                        item = "Yogyakarta"
                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    },
                )
                DropdownMenuItem(
                    content = { Text("Semarang") },
                    onClick = {
                        expanded = false
                        item = "Semarang"
                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    },
                )
            }
    }
    return item
}

@Preview(showBackground = true)
@Composable
fun DropDownDestinationPreview() {
    TraleCapstoneTheme {
        DropDownDestination(text = "seooo")
    }
}