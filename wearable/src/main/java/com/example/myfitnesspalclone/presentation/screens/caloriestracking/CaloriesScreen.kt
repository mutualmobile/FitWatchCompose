package com.example.myfitnesspalclone.presentation.screens.caloriestracking

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.wear.compose.material.*
import com.example.myfitnesspalclone.presentation.ClientDataViewModel
import com.example.myfitnesspalclone.presentation.components.NavButton
import com.example.myfitnesspalclone.presentation.screens.summarylist.TopTitle
import com.example.myfitnesspalclone.presentation.utils.VerticalSpacer

@Composable
fun CaloriesScreen(viewModel: ClientDataViewModel, onAddMealClick: () -> Unit) {
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
                    color = Color(0xFF3f99fc),
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
            progress = 0.1f,
            trackColor = Color(0xFF15202f),
            indicatorColor = Color(0xFF339aff)
        )
    }
}