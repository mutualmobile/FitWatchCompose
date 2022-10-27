package com.example.fitwatch.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import com.example.fitwatch.presentation.theme.NavButtonBGColor
import com.example.fitwatch.presentation.theme.NavButtonIconTint

@Composable
fun NavButton(imageVector: ImageVector, onButtonClick: () -> Unit) {
    Button(
        modifier = Modifier
            .size(ButtonDefaults.DefaultButtonSize),
        onClick = { onButtonClick() },
        colors = ButtonDefaults.primaryButtonColors(
            backgroundColor = NavButtonBGColor,
        )
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = "triggers phone action",
            tint = NavButtonIconTint
        )
    }
}