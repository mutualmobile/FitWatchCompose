/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.myfitnesspalclone.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.myfitnesspalclone.presentation.navigation.FitnessPalNavHost
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity() {
    private val messageClient by lazy { Wearable.getMessageClient(this) }
    private val clientDataViewModel by viewModels<ClientDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberSwipeDismissableNavController()
            FitnessPalNavHost(navController = navHostController, events = clientDataViewModel.events)
//            Toast.makeText(LocalContext.current, clientDataViewModel.events[0].text ?: "nothing", Toast.LENGTH_LONG).show()
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
}
