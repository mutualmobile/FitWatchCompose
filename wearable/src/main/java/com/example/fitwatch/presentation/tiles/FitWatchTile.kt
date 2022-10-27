package com.example.fitwatch.presentation.tiles

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
import androidx.glance.wear.tiles.curved.AnchorType
import androidx.glance.wear.tiles.curved.CurvedRow
import androidx.glance.wear.tiles.curved.GlanceCurvedModifier
import androidx.glance.wear.tiles.curved.sweepAngleDegrees
import androidx.glance.wear.tiles.curved.thickness
import com.example.fitwatch.presentation.theme.CustomBlue
import com.example.fitwatch.presentation.theme.NutrientsProgressBGColor

class FitWatchTile : GlanceTileService() {

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

                GlanceCircleBox(value = 350, text = "carbs", target = 500)

                GlanceHorizontalSpacer(width = 4.dp)

                GlanceCircleBox(10, "fat", target = 30)

                GlanceHorizontalSpacer(width = 4.dp)

                GlanceCircleBox(32, "protein", target = 60)

            }

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                GlanceCircleBox(780, "cal", target = 1800)

                GlanceHorizontalSpacer(width = 4.dp)

                GlanceCircleBox(6, "water", target = 10)

            }
        }
    }
}

@Composable
fun GlanceCircleBox(value: Int, text: String, target: Int) {
    Box(
        modifier = GlanceModifier
            .size(58.dp),
        contentAlignment = Alignment.Center
    ) {

        val sweepProgress = (value.toFloat() / target.toFloat()) * 360f

        CurvedRow {
            curvedLine(
                color = ColorProvider(NutrientsProgressBGColor),
                curvedModifier =
                GlanceCurvedModifier
                    .sweepAngleDegrees(360f)
                    .thickness(3.dp)
            )
        }

        CurvedRow(
            anchorDegrees = 270f,
            anchorType = AnchorType.Start
        ) {
            curvedLine(
                color = ColorProvider(CustomBlue),
                curvedModifier =
                GlanceCurvedModifier
                    .sweepAngleDegrees(sweepProgress)
                    .thickness(3.dp)
            )
        }

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