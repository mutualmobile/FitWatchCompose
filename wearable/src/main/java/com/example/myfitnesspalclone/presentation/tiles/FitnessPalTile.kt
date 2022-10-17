package com.example.myfitnesspalclone.presentation.tiles

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.glance.wear.tiles.GlanceTileService

class FitnessPalTile : GlanceTileService() {

    @Composable
    override fun Content() {
        Column(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.Top
        ) {
            GlanceTopTile(title = "Today", subtitle = "Summary")

            GlanceVerticalSpacer(height = 8.dp)

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlanceCircleBox(value = 0, text = "carbs", progress = 0f)

                GlanceHorizontalSpacer(width = 4.dp)

                GlanceCircleBox(0, "fat", 0f)

                GlanceHorizontalSpacer(width = 4.dp)

                GlanceCircleBox(0, "protein", 0f)

            }

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                GlanceCircleBox(0, "cal", 0f)

                GlanceHorizontalSpacer(width = 4.dp)

                GlanceCircleBox(0, "water", 0f)

            }
        }
    }
}

@Composable
fun GlanceCircleBox(value: Int, text: String, progress: Float) {
    Box(
        modifier = GlanceModifier
            .size(58.dp),
        contentAlignment = Alignment.Center
    ) {

//        CircularProgressIndicator(
//            modifier = Modifier.size(58.dp),
//            progress = progress
//        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = value.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = ColorProvider(color = Color(0xFF949aa1))
                )
            )
        }
    }
}

@Composable
fun GlanceVerticalSpacer(height: Dp) {
    Spacer(
        modifier = GlanceModifier.height(height = height)
    )
}

@Composable
fun GlanceHorizontalSpacer(width: Dp) {
    Spacer(
        modifier = GlanceModifier.width(width = width)
    )
}

@Composable
fun GlanceTopTile(title: String, subtitle: String) {
    Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold))
    Text(text = subtitle, style = TextStyle(color = ColorProvider(color = Color(0xFF949aa1))))
}