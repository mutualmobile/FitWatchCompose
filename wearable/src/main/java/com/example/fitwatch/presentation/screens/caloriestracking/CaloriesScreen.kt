package com.example.fitwatch.presentation.screens.caloriestracking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.example.fitwatch.presentation.ClientDataViewModel
import com.example.fitwatch.presentation.components.NavButton
import com.example.fitwatch.presentation.screens.summarylist.TopTitle
import com.example.fitwatch.presentation.theme.CustomBlue
import com.example.fitwatch.presentation.theme.ProgressTrackColor
import com.example.fitwatch.presentation.utils.VerticalSpacer

@Composable
fun CaloriesScreen(viewModel: ClientDataViewModel, onAddMealClick: () -> Unit) {
    val caloriesCount = viewModel.calories.value
    val caloriesGoal = viewModel.caloriesGoal.value

    val caloriesProgress =
        remember(caloriesCount, caloriesGoal) { mutableStateOf(caloriesCount.toFloat() / caloriesGoal.toFloat()) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 18.dp)

            TopTitle(title = "Today", subtitle = "Calories")

            VerticalSpacer(height = 4.dp)

            Text(
                text = viewModel.calories.value.toString(),
                style = TextStyle(
                    fontSize = 44.sp,
                    color = CustomBlue,
                    fontWeight = FontWeight.Bold
                )
            )

            VerticalSpacer(height = 4.dp)

            NavButton(imageVector = Icons.Rounded.Add) {
                onAddMealClick()
            }
        }

        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            progress = caloriesProgress.value,
            trackColor = ProgressTrackColor,
            indicatorColor = CustomBlue
        )
    }
}