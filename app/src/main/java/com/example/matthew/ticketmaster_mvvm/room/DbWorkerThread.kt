package com.example.matthew.ticketmaster_mvvm.room

import android.os.Handler
import android.os.HandlerThread

/**
 * worker handler to takes care or putting the queries and updates in worker thread.
 *
 * @author Matthew Howells
 */
class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable,onComplete: Runnable?) {
        mWorkerHandler.post(task)
        onComplete?.let {
            mWorkerHandler.post(onComplete)
        }
    }

}