package com.example.fitwatch.presentation.screens.nutrients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.fitwatch.presentation.data.mockNutrientsList
import com.example.fitwatch.presentation.screens.summarylist.TopTitle
import com.example.fitwatch.presentation.theme.NutrientsProgressBGColor
import com.example.fitwatch.presentation.theme.Red400
import com.example.fitwatch.presentation.theme.Teal200
import com.example.fitwatch.presentation.utils.VerticalSpacer

@Composable
fun NutrientsScreen() {
    val scalingLazyListState = rememberScalingLazyListState()

    Scaffold(
        positionIndicator = { PositionIndicator(scalingLazyListState = scalingLazyListState) },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        ScalingLazyColumn(state = scalingLazyListState) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VerticalSpacer(height = 16.dp)
                    TopTitle(title = "Today", subtitle = "Nutrients")
                }
            }

            items(mockNutrientsList.size) {
                val currentNutrient = mockNutrientsList[it]
                NutrientRow(
                    name = currentNutrient.name,
                    value = currentNutrient.value,
                    target = currentNutrient.target,
                    color = currentNutrient.color
                )
            }
        }
    }
}

@Composable
fun NutrientRow(
    name: String,
    value: Float,
    target: Float,
    color: Color = NutrientsProgressBGColor
) {
    Column(modifier = Modifier.fillMaxSize()) {
        VerticalSpacer(height = 4.dp)

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name)
            Text(text = "${value.toInt()}/${target.toInt()}")
        }

        LinearProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .height(2.dp),
            progress = value/target,
            backgroundColor = color,
            color = if(name == "Carbohydrates") Teal200 else Red400
        )

    }
}