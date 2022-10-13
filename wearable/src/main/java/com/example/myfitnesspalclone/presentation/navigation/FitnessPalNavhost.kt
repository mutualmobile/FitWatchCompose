package com.example.myfitnesspalclone.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.example.myfitnesspalclone.presentation.Event
import com.example.myfitnesspalclone.presentation.screens.caloriestracking.CaloriesScreen
import com.example.myfitnesspalclone.presentation.screens.caloriestracking.CaloriesTrackingScreen
import com.example.myfitnesspalclone.presentation.screens.featureslist.FeaturesList
import com.example.myfitnesspalclone.presentation.screens.meals.MealsScreen
import com.example.myfitnesspalclone.presentation.screens.nutrients.NutrientsScreen
import com.example.myfitnesspalclone.presentation.screens.summarylist.SummaryScreen
import com.example.myfitnesspalclone.presentation.screens.watertracking.WaterTrackingScreen

@Composable
fun FitnessPalNavHost(navController: NavHostController, events: List<Event>) {
    SwipeDismissableNavHost(
        navController = navController, startDestination = Screens.FeatureListScreen.route
    ) {
        composable(Screens.FeatureListScreen.route) {
            FeaturesList() { id ->
                when (id) {
                    0 -> {
                        navController.navigate(Screens.SummaryScreen.route)
                    }

                    1 -> {
                        navController.navigate(Screens.NutrientsScreen.route)
                    }

                    2 -> {
                        navController.navigate(Screens.CaloriesScreen.route)
                    }

                    3 -> {
                        navController.navigate(Screens.WaterTrackingScreen.route)
                    }
                }
            }
        }

        composable(Screens.SummaryScreen.route) {
            SummaryScreen()
        }

        composable(Screens.CaloriesScreen.route) {
            CaloriesScreen() {
                navController.navigate(Screens.MealsScreen.route)
            }
        }

        composable(Screens.MealsScreen.route) {
            MealsScreen() {
                navController.navigate(Screens.CaloriesTrackingScreen.route)
            }
        }

        composable(Screens.CaloriesTrackingScreen.route) {
            CaloriesTrackingScreen() {
                navController.popBackStack(
                    route = Screens.CaloriesScreen.route,
                    inclusive = false
                )
            }
        }

        composable(Screens.WaterTrackingScreen.route) {
            WaterTrackingScreen()
        }

        composable(Screens.NutrientsScreen.route) {
            NutrientsScreen()
        }
    }
}


