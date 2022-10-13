package com.example.myfitnesspalclone.presentation.screens.nutrients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.myfitnesspalclone.presentation.navigation.Screens
import com.example.myfitnesspalclone.presentation.screens.summarylist.TopTitle
import com.example.myfitnesspalclone.presentation.utils.VerticalSpacer
import com.google.android.material.progressindicator.LinearProgressIndicator

@Composable
fun NutrientsScreen() {
    val scalingLazyListState = rememberScalingLazyListState()


    Scaffold(
        positionIndicator = { PositionIndicator(scalingLazyListState = scalingLazyListState) }
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

            items(15) {
                NutrientRow()
            }
        }
    }
}

@Composable
fun NutrientRow() {
    Column(modifier = Modifier.fillMaxSize()) {
        VerticalSpacer(height = 4.dp)

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Protein")
            Text(text = "0/419")
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .height(2.dp)
                .fillMaxWidth()
                .background(color = Color.White)
        )

    }
}