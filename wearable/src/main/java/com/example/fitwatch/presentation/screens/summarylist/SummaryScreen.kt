package com.example.fitwatch.presentation.screens.summarylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.example.fitwatch.presentation.ClientDataViewModel
import com.example.fitwatch.presentation.theme.CustomBlue
import com.example.fitwatch.presentation.theme.SubtitleTextColor
import com.example.fitwatch.presentation.utils.HorizontalSpacer
import com.example.fitwatch.presentation.utils.VerticalSpacer

@Composable
fun SummaryScreen(viewModel: ClientDataViewModel) {
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
            CircleBox(value = 0, text = "carbs", goal = null)

            HorizontalSpacer(width = 4.dp)

            CircleBox(0, "fat", goal = null)

            HorizontalSpacer(width = 4.dp)

            CircleBox(0, "protein", goal = null)


        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CircleBox(viewModel.calories.value, "cal", viewModel.caloriesGoal.value)

            HorizontalSpacer(width = 4.dp)

            CircleBox(viewModel.waterValue.value, "water", viewModel.waterGoal.value)

        }

    }

}

@Composable
fun CircleBox(value: Int, text: String, goal: Int?) {

    val progress: Float by remember(value, goal) {
        try {
            mutableStateOf((value.toFloat() / goal!!.toFloat()))
        } catch(e: NullPointerException) {
            return@remember mutableStateOf(0f)
        }
    }

    Box(
        modifier = Modifier
            .size(58.dp),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier.matchParentSize(),
            progress = progress,
            indicatorColor = CustomBlue
        )

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
                    color = SubtitleTextColor
                )
            )
        }
    }
}

@Composable
fun TopTitle(title: String, subtitle: String) {
    Text(text = title, style = TextStyle(fontWeight = Bold))
    Text(text = subtitle, style = TextStyle(color = SubtitleTextColor))
}