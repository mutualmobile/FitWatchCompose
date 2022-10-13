package com.example.myfitnesspalclone

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import com.example.myfitnesspalclone.ui.theme.MyFitnessPalCloneTheme
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    private val messageClient by lazy { Wearable.getMessageClient(this) }
    private val nodeClient by lazy { Wearable.getNodeClient(this) }
    private val clientDataViewModel by viewModels<ClientDataViewModel>()

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

                        Button(onClick = { sendCaloriesData(textValue) }) {
                            Text(text = "Send")
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        messageClient.addListener(clientDataViewModel)
    }

    override fun onPause() {
        super.onPause()
        messageClient.removeListener(clientDataViewModel)
    }

    private fun sendCaloriesData(calories: Int) {
        lifecycleScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()

                nodes.map {node ->
                    async {
                        messageClient.sendMessage(node.id, CALORIES_PATH, calories.toString().toByteArray())
                            .await()
                    }
                }.awaitAll()
                Log.d(TAG, "Calories data requests sent successfully")
            }catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (exception: Exception) {
                Log.d(TAG, "Sending calories data failed: $exception")
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val CALORIES_PATH = "/calories"
        private const val CALORIES_KEY = "calories_key"
    }
}