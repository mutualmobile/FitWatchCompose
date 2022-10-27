package com.example.fitwatch.presentation.data

import androidx.compose.ui.graphics.Color

data class Nutrient(val name: String, val value: Float, val target: Float, val color: Color = Color(0xFF232323))

val mockNutrientsList = listOf(
    Nutrient(
        name = "Protein",
        value = 150f,
        target = 419f,
        color = Color(0xFF3b2607)
    ),
    Nutrient(
        name = "Carbohydrates",
        value = 390f,
        target = 1000f,
        color = Color(0xFF002a27)
    ),
    Nutrient(
        name = "Fibre",
        value = 12f,
        target = 30f
    ),
    Nutrient(
        name = "Sugars",
        value = 150f,
        target = 314f
    ),
    Nutrient(
        name = "Fat",
        value = 150f,
        target = 279f,
        color = Color(0xFF2e1832)
    ),
    Nutrient(
        name = "Saturated",
        value = 31f,
        target = 93f
    ),
    Nutrient(
        name = "Polyunsaturated",
        value = 0f,
        target = 0f
    ),
    Nutrient(
        name = "Monounsaturated",
        value = 0f,
        target = 0f
    ),
    Nutrient(
        name = "Trans",
        value = 0f,
        target = 0f
    ),
    Nutrient(
        name = "Cholesterol",
        value = 150f,
        target = 300f
    ),
    Nutrient(
        name = "Sodium",
        value = 150f,
        target = 2300f
    ),
    Nutrient(
        name = "Potassium",
        value = 150f,
        target = 350f
    ),
    Nutrient(
        name = "Vitamin A",
        value = 23f,
        target = 100f
    ),
    Nutrient(
        name = "Vitamin C",
        value = 39f,
        target = 100f
    ),
    Nutrient(
        name = "Calcium",
        value = 76f,
        target = 100f
    ),
    Nutrient(
        name = "Iron",
        value = 81f,
        target = 100f
    ),
)


