package com.example.myfitnesspalclone.presentation.screens.meals

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Picker
import androidx.wear.compose.material.PickerDefaults
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberPickerState
import com.example.myfitnesspalclone.presentation.components.NavButton
import com.example.myfitnesspalclone.presentation.navigation.Screens
import com.example.myfitnesspalclone.presentation.utils.VerticalSpacer

@Composable
fun MealsScreen(
    onNavButtonClick: () -> Unit
) {
    val pickerOptions = remember {
        mutableStateListOf(
            "Breakfast",
            "Lunch",
            "Dinner",
            "Snacks"
        )
    }

    val pickerState = rememberPickerState(
        initialNumberOfOptions = 4,
        initiallySelectedOption = 0,
        repeatItems = false
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(height = 12.dp)

        Text(
            text = "Meals",
            color = Color(0xFFb3b3b3),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        VerticalSpacer(height = (12.dp))

        Picker(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            state = pickerState,
            scalingParams = PickerDefaults.scalingParams(
                maxElementHeight = 1.5f
            )
        ) {
            Text(text = pickerOptions[this.selectedOption])
        }

        VerticalSpacer(height = (12.dp))

        NavButton(imageVector = Icons.Default.ArrowForward) {
            onNavButtonClick()
        }

    }
}

enum class Meals {
    BREAKFAST,
    LUNCH,
    DINNER,
    Snacks
}