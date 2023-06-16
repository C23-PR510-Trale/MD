package com.example.tralecapstone.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PriorityHigh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.ui.theme.*

@Composable
fun EmergencyNumber(
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        border = BorderStroke(2.dp, Yellow)
    ) {
        Row(modifier = Modifier.padding(14.dp)) {
            Icon(
                imageVector = Icons.Rounded.PriorityHigh,
                contentDescription = null,
                tint = Maroon,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
                    .align(CenterVertically)
            ) {
                Text(
                    text = "Emergency Number",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        color = Maroon,
                    ),
                    fontSize = 16.sp,
                )

                Text(
                    text = "Police : 110\n" +
                            "Ambulance : 118 and 119\n" +
                            "Trale CS : 081234567890",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 3.dp)
                )

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmergencyNumberPreview() {
    TraleCapstoneTheme {
        EmergencyNumber()
    }
}