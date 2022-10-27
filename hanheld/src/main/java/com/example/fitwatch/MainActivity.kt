package com.example.fitwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
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
import com.example.fitwatch.ui.theme.FitnessWatchTheme
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity() {

    private val dataClient by lazy { Wearable.getDataClient(this) }
    private val viewModel by viewModels<ClientDataViewModel>()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var caloriesValue by remember { mutableStateOf("") }
            var caloriesGoal by remember { mutableStateOf("") }

            var waterValue = viewModel.waterValue.value
            var waterGoal by remember { mutableStateOf("") }

            val keyboardController = LocalSoftwareKeyboardController.current

            FitnessWatchTheme {
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
                            value = caloriesGoal,
                            onValueChange = {
                                caloriesGoal = it
                            },
                            placeholder = { Text(text = "Calories Goal") },
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

                        OutlinedTextField(
                            value = caloriesValue,
                            onValueChange = {
                                caloriesValue = it
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

                        Button(onClick = {
                            viewModel.updateCalories(
                                dataClient,
                                caloriesValue.toInt(),
                                caloriesGoal.toInt()
                            )
                        }) {
                            Text(text = "Sync Calories")
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(text = "Calories: ${viewModel.calories.value}", fontSize = 48.sp)

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = waterGoal.toString(),
                            onValueChange = {
                                waterGoal = it
                            },
                            placeholder = { Text(text = "Water Goal") },
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

                        Button(onClick = {
                            viewModel.updateWater(
                                dataClient,
                                waterValue,
                                waterGoal.toInt()
                            )
                        }) {
                            Text(text = "Sync Water Goal")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 64.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.updateWater(
                                        dataClient,
                                        waterGoal = waterGoal.toInt(),
                                        waterValue = waterValue - 1
                                    )
                                }
                            ) {
                                Icon(imageVector = Icons.Filled.Remove, contentDescription = "")
                            }

                            Text(text = "Water: $waterValue", fontSize = 24.sp)

                            IconButton(
                                onClick = {
                                    viewModel.updateWater(
                                        dataClient,
                                        waterGoal = waterGoal.toInt(),
                                        waterValue = waterValue + 1
                                    )
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "")
                            }
                        }
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