package com.example.fitwatch.presentation.screens.meals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Picker
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberPickerState
import com.example.fitwatch.presentation.components.NavButton
import com.example.fitwatch.presentation.theme.CustomBlue
import com.example.fitwatch.presentation.theme.MealsTextColor
import com.example.fitwatch.presentation.utils.VerticalSpacer

@Composable
fun MealsScreen(
    onNavButtonClick: (meal: String) -> Unit
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
            color = MealsTextColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        VerticalSpacer(height = (12.dp))

        Picker(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            state = pickerState,
        ) {
            MealsPicker(text = pickerOptions[it], style = MaterialTheme.typography.title2)
        }

        VerticalSpacer(height = (12.dp))

        NavButton(imageVector = Icons.Default.ArrowForward) {
            onNavButtonClick(pickerOptions[pickerState.selectedOption])
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MealsPicker(
    text: String,
    style: TextStyle
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val modifier = Modifier
            .align(Alignment.Center)
            .wrapContentSize()
        Text(
            text = text,
            maxLines = 1,
            color = CustomBlue,
            style = style,
            modifier = modifier
        )
    }
}

enum class Meals {
    BREAKFAST,
    LUNCH,
    DINNER,
    Snacks
}