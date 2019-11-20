package com.example.android.testapplication

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.os.SystemClock
import android.util.Log


class ExampleHandlerThread: HandlerThread("ExampleHandlerThread") {
    private val TAG = "ExampleHandlerThread"

    private var handler: Handler? = null

    override fun onLooperPrepared() {
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    Companion.EXAMPLE_TASK -> {
                        Log.d(TAG, "Example Task, arg1: " + msg.arg1 + ", obj: " + msg.obj)
                        for (i in 0..3) {
                            Log.d(TAG, "handleMessage: $i")
                            SystemClock.sleep(1000)
                        }
                    }
                }
            }
        }
    }

    fun getHandler() = handler

    companion object {
        val EXAMPLE_TASK = 1
    }
}