package com.example.fitwatch.presentation.navigation

sealed class Screens(val route: String) {
    object FeatureListScreen: Screens("features_screen")
    object SummaryScreen: Screens("summary_screen")
    object NutrientsScreen: Screens("nutrients_screen")
    object CaloriesScreen: Screens("calories_screen")
    object MealsScreen: Screens("meals_screen")
    object CaloriesTrackingScreen: Screens("calories_tracking_screen/{selectedMeal}")
    object WaterTrackingScreen: Screens("water_screen")
}
