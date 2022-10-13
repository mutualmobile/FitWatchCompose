package com.example.myfitnesspalclone.presentation.screens.summarylist

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.myfitnesspalclone.presentation.utils.HorizontalSpacer
import com.example.myfitnesspalclone.presentation.utils.VerticalSpacer

@Composable
fun SummaryScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(height = 12.dp)

        TopTitle(title = "Today", subtitle = "Summary")

        VerticalSpacer(height = 8.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CircleBox(value = 0, text = "carbs", progress = 0f)

            HorizontalSpacer(width = 4.dp)

            CircleBox(0, "fat", 0f)

            HorizontalSpacer(width = 4.dp)

            CircleBox(0, "protein", 0f)


        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CircleBox(0, "cal", 0f)

            HorizontalSpacer(width = 4.dp)

            CircleBox(0, "water", 0f)

        }

    }

}

@Composable
fun CircleBox(value: Int, text: String, progress: Float) {
    Box(
        modifier = Modifier
            .size(58.dp)
            .border(
                width = 4.dp,
                color = Color(0xFF383838),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = value.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = Bold
                )
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color(0xFF949aa1)
                )
            )
        }
    }
}

@Composable
fun TopTitle(title: String, subtitle: String) {
    Text(text = title, style = TextStyle(fontWeight = Bold))
    Text(text = subtitle, style = TextStyle(color = Color(0xFF949aa1)))
}