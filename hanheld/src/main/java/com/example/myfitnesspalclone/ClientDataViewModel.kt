package com.example.myfitnesspalclone

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.PutDataMapRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException

class ClientDataViewModel: ViewModel(), DataClient.OnDataChangedListener {

    val calories = mutableStateOf(0)
    override fun onDataChanged(dataEvents: DataEventBuffer) {
        viewModelScope.launch {
            dataEvents.forEach { event ->
                if (event.type == DataEvent.TYPE_CHANGED) {
                    // DataItem Changed
                    val item = event.dataItem
                    if (item.uri.path?.compareTo(CALORIES_PATH) == 0) {
                        val dataMap = DataMapItem.fromDataItem(item).dataMap
                        calories.value = dataMap.getInt(CALORIES_KEY)
                    }
                }
            }
        }
    }

    fun updateCalories(dataClient: DataClient, calories: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val putDataMapRequest = PutDataMapRequest.create(CALORIES_PATH)
            putDataMapRequest.dataMap.putInt(CALORIES_KEY, calories)
            val putDataReq = putDataMapRequest.asPutDataRequest()
            putDataReq.setUrgent()
            val putDataTask = dataClient.putDataItem(putDataReq)
            try {
                Tasks.await(putDataTask).apply {
                    Log.d("UpdateCalories", "updateCalories: Success, value: $calories")
                }
            }
            catch (e: ExecutionException) {
                Log.d("UpdateCalories", "updateCalories: Failure ${e.printStackTrace()}")
            }
            catch (e: InterruptedException) {
                Log.d("UpdateCalories", "updateCalories: Failure ${e.printStackTrace()}")
            }
        }
    }

    companion object {
        const val CALORIES_KEY = "calories_key"
        const val CALORIES_PATH = "/calories_count"
    }
}