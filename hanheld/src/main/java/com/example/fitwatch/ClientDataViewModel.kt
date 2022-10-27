package com.example.fitwatch

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

class ClientDataViewModel : ViewModel(), DataClient.OnDataChangedListener {

    val calories = mutableStateOf(0)
    val waterValue = mutableStateOf(0)
    override fun onDataChanged(dataEvents: DataEventBuffer) {
        getCalories(dataEvents)
        getWaterValue(dataEvents)
    }

    fun updateCalories(dataClient: DataClient, calories: Int, caloriesGoal: Int) {
        updateCaloriesValue(dataClient, calories)
        updateCaloriesGoal(dataClient, caloriesGoal)
    }

    fun updateWater(dataClient: DataClient, waterValue: Int, waterGoal: Int) {
        updateWaterValue(dataClient, waterValue)
        updateWaterGoal(dataClient, waterGoal)
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

    private fun updateCaloriesValue(dataClient: DataClient, calories: Int) {
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
            } catch (e: ExecutionException) {
                Log.d("UpdateCalories", "updateCalories: Failure ${e.printStackTrace()}")
            } catch (e: InterruptedException) {
                Log.d("UpdateCalories", "updateCalories: Failure ${e.printStackTrace()}")
            }
        }
    }

    private fun updateCaloriesGoal(dataClient: DataClient, caloriesGoal: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val putDataMapRequest = PutDataMapRequest.create(CALORIES_GOAL_PATH)
            putDataMapRequest.dataMap.putInt(CALORIES_GOAL_KEY, caloriesGoal)
            val putDataReq = putDataMapRequest.asPutDataRequest()
            putDataReq.setUrgent()
            val putDataTask = dataClient.putDataItem(putDataReq)
            try {
                Tasks.await(putDataTask).apply {
                    Log.d("UpdateCaloriesGoal", "updateCaloriesGoal: Success, value: $caloriesGoal")
                }
            } catch (e: ExecutionException) {
                Log.d("UpdateCaloriesGoal", "updateCaloriesGoal: Failure ${e.printStackTrace()}")
            } catch (e: InterruptedException) {
                Log.d("UpdateCaloriesGoal", "updateCaloriesGoal: Failure ${e.printStackTrace()}")
            }
        }
    }

    private fun updateWaterValue(dataClient: DataClient, waterValue: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val putDataMapRequest = PutDataMapRequest.create(WATER_PATH)
            putDataMapRequest.dataMap.putInt(WATER_KEY, waterValue)
            val putDataReq = putDataMapRequest.asPutDataRequest()
            putDataReq.setUrgent()
            val putDataTask = dataClient.putDataItem(putDataReq)
            try {
                Tasks.await(putDataTask).apply {
                    Log.d("UpdateWater", "updateWater: Success, value: $waterValue")
                }
            } catch (e: ExecutionException) {
                Log.d("UpdateWater", "updateWater: Failure ${e.printStackTrace()}")
            } catch (e: InterruptedException) {
                Log.d("UpdateWater", "updateWater: Failure ${e.printStackTrace()}")
            }
        }
    }

    private fun updateWaterGoal(dataClient: DataClient, waterGoal: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val putDataMapRequest = PutDataMapRequest.create(WATER_GOAL_PATH)
            putDataMapRequest.dataMap.putInt(WATER_GOAL_KEY, waterGoal)
            val putDataReq = putDataMapRequest.asPutDataRequest()
            putDataReq.setUrgent()
            val putDataTask = dataClient.putDataItem(putDataReq)
            try {
                Tasks.await(putDataTask).apply {
                    Log.d("UpdateWaterGoal", "updateWaterGoal: Success, value: $waterGoal")
                }
            } catch (e: ExecutionException) {
                Log.d("UpdateWaterGoal", "UpdateWaterGoal: Failure ${e.printStackTrace()}")
            } catch (e: InterruptedException) {
                Log.d("UpdateWaterGoal", "UpdateWaterGoal: Failure ${e.printStackTrace()}")
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