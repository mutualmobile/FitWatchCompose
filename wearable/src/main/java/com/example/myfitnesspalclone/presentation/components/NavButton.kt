package com.example.myfitnesspalclone.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon

@Composable
fun NavButton(imageVector: ImageVector, onButtonClick: () -> Unit) {
    Button(
        modifier = Modifier
            .size(ButtonDefaults.DefaultButtonSize),
        onClick = { onButtonClick() },
        colors = ButtonDefaults.primaryButtonColors(
            backgroundColor = Color(0xFF2a2b2e),
        )
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = "triggers phone action",
            tint = Color(0xFFf6f6f6)
        )
    }
}