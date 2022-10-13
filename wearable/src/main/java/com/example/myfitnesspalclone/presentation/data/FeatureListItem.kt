package com.example.myfitnesspalclone.presentation.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.ui.graphics.vector.ImageVector

data class FeatureListItem(
    val icon: ImageVector,
    val title: String
)

val mockFeatureItems = listOf(
    FeatureListItem(
        icon = Icons.Default.Fireplace,
        title = "View summary"
    ),
    FeatureListItem(
        icon = Icons.Default.Fireplace,
        title = "Nutrients"
    ),
    FeatureListItem(
        icon = Icons.Default.Fireplace,
        title = "Add calories"
    ),
    FeatureListItem(
        icon = Icons.Default.Fireplace,
        title = "Add water"
    ),
    FeatureListItem(
        icon = Icons.Default.Fireplace,
        title = "Open on phone"
    ),
    FeatureListItem(
        icon = Icons.Default.Fireplace,
        title = "Log out"
    ),
)
