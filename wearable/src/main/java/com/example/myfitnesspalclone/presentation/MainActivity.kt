/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.myfitnesspalclone.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.myfitnesspalclone.presentation.navigation.FitnessPalNavHost
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity() {
    private val dataClient by lazy { Wearable.getDataClient(this) }
    private val viewModel by viewModels<ClientDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberSwipeDismissableNavController()
            FitnessPalNavHost(navController = navHostController, viewModel = viewModel, dataClient = dataClient)
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
