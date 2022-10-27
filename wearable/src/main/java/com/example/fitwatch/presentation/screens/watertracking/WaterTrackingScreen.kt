package com.example.fitwatch.presentation.screens.watertracking

import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.example.fitwatch.presentation.ClientDataViewModel
import com.example.fitwatch.presentation.components.NavButton
import com.example.fitwatch.presentation.screens.summarylist.TopTitle
import com.example.fitwatch.presentation.theme.CustomBlue
import com.example.fitwatch.presentation.theme.ProgressTrackColor
import com.example.fitwatch.presentation.utils.VerticalSpacer
import com.google.android.gms.wearable.DataClient

@Composable
fun WaterTrackingScreen(viewModel: ClientDataViewModel, dataClient: DataClient) {

    var waterCount = viewModel.waterValue.value
    val waterTarget = viewModel.waterGoal.value
    val progress = (waterCount.toFloat() / waterTarget.toFloat())

    val waterFloatAnim = animateFloatAsState(
        targetValue = if (waterCount == 0) {
            0f
        } else {
            progress
        },
        animationSpec = spring(DampingRatioLowBouncy)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 18.dp)

            TopTitle(title = "Water", subtitle = "Cups")

            VerticalSpacer(height = 4.dp)

            Text(
                text = viewModel.waterValue.value.toString(),
                style = TextStyle(
                    fontSize = 44.sp,
                    color = CustomBlue,
                    fontWeight = FontWeight.Bold
                )
            )

            VerticalSpacer(height = 4.dp)

            NavButton(imageVector = Icons.Rounded.Add) {
                waterCount += 1
                viewModel.updateWater(dataClient = dataClient, waterValue = waterCount)
            }
        }

        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            progress = waterFloatAnim.value,
            trackColor = ProgressTrackColor,
            indicatorColor = CustomBlue
        )
    }
}