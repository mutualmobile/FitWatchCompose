package com.example.fitwatch

import android.content.Intent
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class DataLayerListenerService: WearableListenerService() {

    override fun onMessageReceived(event: MessageEvent) {
        super.onMessageReceived(event)

        when (event.path) {
            START_ACTIVITY_PATH -> {
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }
    }

    companion object {
        private const val START_ACTIVITY_PATH = "/start-activity"
    }
}