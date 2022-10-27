package com.example.fitwatch.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.PutDataMapRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException

class ClientDataViewModel: ViewModel(), DataClient.OnDataChangedListener{

    val calories = mutableStateOf(0)
    val caloriesGoal = mutableStateOf(2000)
    val waterValue = mutableStateOf(0)
    val waterGoal = mutableStateOf(10)

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        getCalories(dataEvents)
        getCaloriesGoals(dataEvents)
        getWaterValue(dataEvents)
        getWaterGoal(dataEvents)
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

    fun updateWater(dataClient: DataClient, waterValue: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val putDataMapRequest = PutDataMapRequest.create(WATER_PATH)
            putDataMapRequest.dataMap.putInt(WATER_KEY, waterValue)
            val putDataReq = putDataMapRequest.asPutDataRequest()
            putDataReq.setUrgent()
            val putDataTask = dataClient.putDataItem(putDataReq)
            try {
                Tasks.await(putDataTask).apply {
                    Log.d("UpdateWater", "updateWaterValue: Success, value: $waterValue")
                }
            }
            catch (e: ExecutionException) {
                Log.d("UpdateWater", "updateWaterValue: Failure ${e.printStackTrace()}")
            }
            catch (e: InterruptedException) {
                Log.d("UpdateWater", "updateWaterValue: Failure ${e.printStackTrace()}")
            }
        }
    }

    private fun getCalories(dataEvents: DataEventBuffer) {
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

    private fun getCaloriesGoals(dataEvents: DataEventBuffer) {
        viewModelScope.launch {
            dataEvents.forEach { event ->
                if (event.type == DataEvent.TYPE_CHANGED) {
                    // DataItem Changed
                    val item = event.dataItem
                    if (item.uri.path?.compareTo(CALORIES_GOAL_PATH) == 0) {
                        val dataMap = DataMapItem.fromDataItem(item).dataMap
                        caloriesGoal.value = dataMap.getInt(CALORIES_GOAL_KEY)
                    }
                }
            }
        }
    }

    private fun getWaterValue(dataEvents: DataEventBuffer) {
        viewModelScope.launch {
            dataEvents.forEach { event ->
                if (event.type == DataEvent.TYPE_CHANGED) {
                    // DataItem Changed
                    val item = event.dataItem
                    if (item.uri.path?.compareTo(WATER_PATH) == 0) {
                        val dataMap = DataMapItem.fromDataItem(item).dataMap
                        waterValue.value = dataMap.getInt(WATER_KEY)
                    }
                }
            }
        }
    }

    private fun getWaterGoal(dataEvents: DataEventBuffer) {
        viewModelScope.launch {
            dataEvents.forEach { event ->
                if (event.type == DataEvent.TYPE_CHANGED) {
                    // DataItem Changed
                    val item = event.dataItem
                    if (item.uri.path?.compareTo(WATER_GOAL_PATH) == 0) {
                        val dataMap = DataMapItem.fromDataItem(item).dataMap
                        waterGoal.value = dataMap.getInt(WATER_GOAL_KEY)
                    }
                }
            }
        }
    }

    companion object {
        const val CALORIES_KEY = "calories_key"
        const val CALORIES_PATH = "/calories_count"

        const val CALORIES_GOAL_KEY = "calories_goal_key"
        const val CALORIES_GOAL_PATH = "/calories_goal"

        const val WATER_KEY = "water_key"
        const val WATER_PATH = "/water_glass_count"

        const val WATER_GOAL_KEY = "water_goal_key"
        const val WATER_GOAL_PATH = "/water_goal_path"
    }
}
