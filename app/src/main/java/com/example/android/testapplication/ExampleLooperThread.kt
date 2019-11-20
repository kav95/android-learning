package com.example.android.testapplication

import android.os.Handler
import android.os.Looper
import android.util.Log

class ExampleLooperThread: Thread() {
    private val TAG = "ExampleLooperThread"

    lateinit var looper: Looper
    lateinit var handler: Handler

    override fun run() {
        Looper.prepare()

        looper = Looper.myLooper()
        handler = ExampleHandler()

        Looper.loop()
        Log.d(TAG, "End of run()");
    }
}