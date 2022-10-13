package com.example.myfitnesspalclone.presentation.screens.featureslist

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
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
    icon: ImageVector,
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
            Icon(imageVector = icon, contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title)
        }
    }
}