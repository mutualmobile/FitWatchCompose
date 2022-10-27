package com.example.fitwatch.presentation.data

data class FeatureListItem(
    val icon: Int,
    val title: String
)

enum class Features(val displayName: String) {
    VIEW_SUMMARY("View summary"),
    NUTRIENTS("Nutrients"),
    ADD_CALORIES("Add calories"),
    ADD_WATER("Add water"),
    OPEN_ON_PHONE("Open on phone"),
    LOG_OUT("Log out")
}