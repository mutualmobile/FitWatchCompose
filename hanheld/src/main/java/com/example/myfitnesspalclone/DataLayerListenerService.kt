package com.example.myfitnesspalclone

import android.content.Intent
import android.widget.Toast
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Node
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

    override fun onPeerConnected(p0: Node) {
        Toast.makeText(this, "Peer connected", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val START_ACTIVITY_PATH = "/start-activity"
    }
}