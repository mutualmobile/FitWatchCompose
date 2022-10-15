package com.example.myfitnesspalclone.presentation.data

import com.example.myfitnesspalclone.R

data class FeatureListItem(
    val icon: Int,
    val title: String
)

val mockFeatureItems = listOf(
    FeatureListItem(
        icon = R.drawable.ic_summary,
        title = "View summary"
    ),
    FeatureListItem(
        icon = R.drawable.ic_align,
        title = "Nutrients"
    ),
    FeatureListItem(
        icon = R.drawable.ic_fire,
        title = "Add calories"
    ),
    FeatureListItem(
        icon = R.drawable.ic_water,
        title = "Add water"
    ),
    FeatureListItem(
        icon = R.drawable.ic_phone,
        title = "Open on phone"
    ),
    FeatureListItem(
        icon = R.drawable.ic_logout,
        title = "Log out"
    ),
)
