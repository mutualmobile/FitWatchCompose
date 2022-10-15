package com.example.myfitnesspalclone.presentation.screens.featureslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
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
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.myfitnesspalclone.presentation.data.mockFeatureItems

@Composable
fun FeaturesList(onClick: (id: Int) -> Unit) {
    val scalingLazyListState = rememberScalingLazyListState()
    Scaffold(
        positionIndicator = {
            PositionIndicator(scalingLazyListState = scalingLazyListState)
        }
    ) {
        ScalingLazyColumn(
            state = scalingLazyListState,
            scalingParams = ScalingLazyColumnDefaults.scalingParams(
                edgeScale = 0.5f,
                minTransitionArea = 0.65f,
                maxTransitionArea = 0.70f
            )
        ) {
            items(mockFeatureItems.size) {
                FeatureCard(
                    modifier = Modifier.padding(
                        top = if (it == 0) {
                            24.dp
                        } else {
                            8.dp
                        }
                    ),
                    mockFeatureItems[it].icon, mockFeatureItems[it].title,
                    id = it,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    id: Int,
    onClick: (id: Int) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onClick(id) }
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