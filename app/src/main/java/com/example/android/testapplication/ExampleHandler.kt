package com.example.android.testapplication

import android.os.Handler
import android.os.Message
import android.util.Log


class ExampleHandler: Handler() {
    private val TAG = "ExampleHandler"

    override fun handleMessage(msg: Message?) {
        when (msg?.what) {
            TASK_A -> Log.d(TAG, "Task A executed")
            TASK_B -> Log.d(TAG, "Task B executed")
        }
    }

    companion object {
        val TASK_A = 1
        val TASK_B = 2
    }
}