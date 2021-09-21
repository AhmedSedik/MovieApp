package com.example.myapplication.data.model

import androidx.lifecycle.Observer
/* gist link
https://gist.github.com/JoseAlcerreca/5b661f1800e1e654f07cc54fe87441af
 */

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {
    private var hasBeenHandled = false
    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { onEventUnhandledContent(it) }
    }
}
