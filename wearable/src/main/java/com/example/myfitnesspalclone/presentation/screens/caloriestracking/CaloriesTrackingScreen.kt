package com.example.myfitnesspalclone.presentation.screens.caloriestracking

import android.app.RemoteInput
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender
import com.example.myfitnesspalclone.R
import com.example.myfitnesspalclone.presentation.ClientDataViewModel
import com.example.myfitnesspalclone.presentation.components.NavButton
import com.example.myfitnesspalclone.presentation.navigation.Screens
import com.example.myfitnesspalclone.presentation.screens.summarylist.TopTitle
import com.example.myfitnesspalclone.presentation.utils.VerticalSpacer

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CaloriesTrackingScreen(
    viewModel: ClientDataViewModel,
    onDoneButtonClick: (calories: Int) -> Unit
) {
//    var textFieldValue by remember { mutableStateOf("") }
//    val keyboardController = LocalSoftwareKeyboardController.current
    val inputTextKey = "input_number"

    var textForUserInput by remember { mutableStateOf("") }

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.let { data ->
                val results: Bundle = RemoteInput.getResultsFromIntent(data)
                val newInputText: CharSequence? = results.getCharSequence(inputTextKey)
                textForUserInput = newInputText as String
            }
        }

    val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput.Builder(inputTextKey)
            .setLabel("57")
            .wearableExtender {
                setEmojisAllowed(false)
                setInputActionType(EditorInfo.IME_ACTION_DONE)
            }.build()
    )

    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)


    var buttonClicked by remember { mutableStateOf(false) }

    val fullProgress = animateFloatAsState(
        targetValue = if (buttonClicked) 1.0f else 0.0f,
        tween(500)
    )

    LaunchedEffect(key1 = fullProgress.value) {
        if(fullProgress.value == 1.0f) {
            onDoneButtonClick(viewModel.calories.value)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 4.dp)

            TopTitle(title = "Add", subtitle = "Calories")

            VerticalSpacer(height = 4.dp)

            Box(
                modifier = Modifier.clickable {
                    launcher.launch(intent)
                },
                contentAlignment = Alignment.Center
            ) {
//            BasicTextField(
//                value = textFieldValue,
//                onValueChange = {
//                    textFieldValue = it
//                },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done,
//                    keyboardType = KeyboardType.Number
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    }
//                ),
//                decorationBox = { innerTextField ->
//                    Box(
//                        Modifier
//                            .background(Color.LightGray, RoundedCornerShape(percent = 30))
//                            .padding(16.dp)
//                    ) {
//
//                        if (textFieldValue.isEmpty()) {
//                            Text(text = "ex. 250", color = Color(0xFF808080), fontSize = 40.sp)
//                        }
//                        innerTextField(
//
//                        ) //<-- Add this
//                    }
//                },
//            )

                if (textForUserInput.isNotEmpty()) {
                    viewModel.calories.value = textForUserInput.toInt()
                    Text(
                        text = viewModel.calories.value.toString(),
                        fontSize = 40.sp,
                        lineHeight = 40.sp
                    )
                } else {
                    Text(text = "ex. 250", color = Color(0xFF808080), fontSize = 40.sp)
                }
            }


            VerticalSpacer(height = 4.dp)

            Text(
                text = "cal for Breakfast",
                color = Color(0xFF339aff),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            VerticalSpacer(height = 4.dp)

            NavButton(imageVector = Icons.Default.Check) {
                buttonClicked = true

            }
        }

        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = fullProgress.value
        )
    }


}