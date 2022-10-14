package com.example.myfitnesspalclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfitnesspalclone.ui.theme.MyFitnessPalCloneTheme
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity() {

    private val dataClient by lazy { Wearable.getDataClient(this) }
    private val viewModel by viewModels<ClientDataViewModel>()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var textValue by remember { mutableStateOf(0) }
                val keyboardController = LocalSoftwareKeyboardController.current

            MyFitnessPalCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = textValue.toString(),
                            onValueChange = {
                                textValue = it.toInt()
                            },
                            placeholder = { Text(text = "Calories") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Button(onClick = { viewModel.updateCalories(dataClient, textValue) }) {
                            Text(text = "Send")
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(text = "Calories: ${viewModel.calories.value}", fontSize = 48.sp)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dataClient.addListener(viewModel)
    }

    override fun onPause() {
        super.onPause()
        dataClient.removeListener(viewModel)
    }




}