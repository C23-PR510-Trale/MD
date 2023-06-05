package com.example.tralecapstone.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddPost(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = query,
        onValueChange = onQueryChange,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                "Share your trip experience here!",
                fontSize = 14.sp
            )
        },
        modifier = modifier
            .padding(
                start = 26.dp,
                end = 26.dp,
                top = 20.dp
            )
            .fillMaxWidth()
            .heightIn(min = 30.dp)
            .shadow(
                10.dp,
                shape = RoundedCornerShape(10.dp),
                clip = true,
                ambientColor = DarkGrey,
                spotColor = DarkGrey
            )
            .clip(RoundedCornerShape(10.dp))

    )
}

@Composable
@Preview(showBackground = true)
fun AddPostPreview() {
    TraleCapstoneTheme() {
        AddPost(
            "",
            {}
        )
    }
}