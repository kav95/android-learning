package com.example.android.testapplication

import android.os.Bundle
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android.testapplication.ExampleHandlerThread.Companion.EXAMPLE_TASK

class HandlerThreadActivity: AppCompatActivity() {
    private val TAG = "HandlerThreadActivity"

    val exampleHandlerThread = ExampleHandlerThread()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler_thread)

        exampleHandlerThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        exampleHandlerThread.quit()
    }


    fun doWork(view: View) {
        val msg = Message.obtain(exampleHandlerThread.getHandler())
        msg.what = EXAMPLE_TASK

        msg.sendToTarget()

        exampleHandlerThread.getHandler()?.post {
            for (i in 0..4) {
                Log.d(TAG, "Runnable1: $i");
                SystemClock.sleep(1000);
            }
        }

        val msg2 = Message.obtain(exampleHandlerThread.getHandler())
        msg2.what = EXAMPLE_TASK

        msg2.sendToTarget()
    }

    fun removeMessages(view: View) {
        exampleHandlerThread.getHandler()?.removeMessages(EXAMPLE_TASK)
    }

}