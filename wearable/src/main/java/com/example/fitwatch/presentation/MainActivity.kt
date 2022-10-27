/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.fitwatch.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Confirmation
import androidx.wear.compose.material.dialog.Dialog
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.fitwatch.R
import com.example.fitwatch.presentation.navigation.FitWatchNavHost
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class MainActivity : ComponentActivity() {
    private val dataClient by lazy { Wearable.getDataClient(this) }
    private val messageClient by lazy { Wearable.getMessageClient(this) }
    private val nodeClient by lazy { Wearable.getNodeClient(this) }

    private val viewModel by viewModels<ClientDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var confirmationShowDialog by remember { mutableStateOf(false) }

            val navHostController = rememberSwipeDismissableNavController()
            FitWatchNavHost(
                navController = navHostController,
                viewModel = viewModel,
                dataClient = dataClient,
                onStartHandheldActivity = {
                    lifecycleScope.launch {
                        try {
                            val nodes = nodeClient.connectedNodes.await()

                            nodes.map { node ->
                                async {
                                    messageClient.sendMessage(node.id, START_ACTIVITY_PATH, byteArrayOf())
                                        .await()
                                }
                            }.awaitAll()
                            if(nodes.size != 0) {
                                Log.d(TAG, "Starting activity requests sent successfully")
                                confirmationShowDialog = true
                            }
                        } catch (cancellationException: CancellationException) {
                            confirmationShowDialog = false
                            throw cancellationException
                        } catch (exception: Exception) {
                            Log.d(TAG, "Starting activity failed: $exception")
                            confirmationShowDialog = false
                        }
                    }
                }
            )

            Dialog(
                showDialog = confirmationShowDialog,
                onDismissRequest = { confirmationShowDialog = false }
            ) {
                Confirmation(
                    onTimeout = {
                        confirmationShowDialog = false
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(R.string.confirmation_dialog_tick),
                            modifier = Modifier.size(48.dp)
                        )
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.confirmation_dialog_success),
                        textAlign = TextAlign.Center
                    )
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

    companion object {
        private const val TAG = "MainActivity"
        private const val START_ACTIVITY_PATH = "/start-activity"
    }
}