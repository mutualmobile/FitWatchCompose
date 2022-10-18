package com.example.myfitnesspalclone.presentation.data

import com.example.myfitnesspalclone.R

data class FeatureListItem(
    val icon: Int,
    val title: String
)

val mockFeatureItems = listOf(
    FeatureListItem(
        icon = R.drawable.ic_summary,
        title = Features.VIEW_SUMMARY.displayName
    ),
    FeatureListItem(
        icon = R.drawable.ic_align,
        title = Features.NUTRIENTS.displayName
    ),
    FeatureListItem(
        icon = R.drawable.ic_fire,
        title = Features.ADD_CALORIES.displayName
    ),
    FeatureListItem(
        icon = R.drawable.ic_water,
        title = Features.ADD_WATER.displayName
    ),
    FeatureListItem(
        icon = R.drawable.ic_phone,
        title = Features.OPEN_ON_PHONE.displayName
    ),
    FeatureListItem(
        icon = R.drawable.ic_logout,
        title = Features.LOG_OUT.displayName
    ),
)

enum class Features(val displayName: String) {
    VIEW_SUMMARY("View summary"),
    NUTRIENTS("Nutrients"),
    ADD_CALORIES("Add calories"),
    ADD_WATER("Add water"),
    OPEN_ON_PHONE("Open on phone"),
    LOG_OUT("Log out")
}