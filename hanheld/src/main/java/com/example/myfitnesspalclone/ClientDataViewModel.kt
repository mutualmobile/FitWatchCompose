package com.example.myfitnesspalclone

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent

class ClientDataViewModel: ViewModel(), MessageClient.OnMessageReceivedListener {
    private val _events = mutableStateListOf<Event>()

    /**
     * The list of events from the clients.
     */
    val events: List<Event> = _events

    override fun onMessageReceived(messageEvent: MessageEvent) {
        _events.add(
            Event(
                title = "Calories",
                text = messageEvent.toString()
            )
        )
    }
}

/**
 * A data holder describing a client event.
 */
data class Event(
     val title: String,
    val text: String
)
