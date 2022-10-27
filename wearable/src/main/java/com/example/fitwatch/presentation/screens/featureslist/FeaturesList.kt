package com.example.fitwatch.presentation.screens.featureslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyColumnDefaults
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.fitwatch.R
import com.example.fitwatch.presentation.data.FeatureListItem
import com.example.fitwatch.presentation.data.Features

@Composable
fun FeaturesList(onClick: (item: String) -> Unit) {
    val scalingLazyListState = rememberScalingLazyListState()
    val featureItems = remember {
        mutableStateListOf(
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
    }

    Scaffold(
        positionIndicator = {
            PositionIndicator(scalingLazyListState = scalingLazyListState)
        },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        ScalingLazyColumn(
            state = scalingLazyListState,
            scalingParams = ScalingLazyColumnDefaults.scalingParams(
                edgeScale = 0.5f,
                minTransitionArea = 0.65f,
                maxTransitionArea = 0.70f
            ),
        ) {
            featureItems.forEachIndexed { index, featureListItem ->
                item(key = featureListItem.title) {
                    FeatureCard(
                        modifier = Modifier.padding(
                            top = if (index == 0) {
                                24.dp
                            } else {
                                8.dp
                            }
                        ),
                        featureListItem.icon, featureListItem.title,
                        onClick = {
                            onClick(featureListItem.title)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title)
        }
    }
}