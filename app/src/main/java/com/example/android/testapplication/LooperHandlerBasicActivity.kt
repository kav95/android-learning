package com.example.android.testapplication

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LooperHandlerBasicActivity: AppCompatActivity() {
    val exampleLooperThread = ExampleLooperThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_layout_for_handler_looper)
    }

    fun startThread(view: View) {
        exampleLooperThread.start()
    }

    fun stopThread(view: View) {
        exampleLooperThread.looper.quit()
    }

    fun taskA(view: View) {
        val m = Message.obtain()
        m.what = ExampleHandler.TASK_A
        exampleLooperThread.handler.sendMessage(m)
    }

    fun taskB(view: View) {
        val m = Message.obtain()
        m.what = ExampleHandler.TASK_B
        exampleLooperThread.handler.sendMessage(m)
    }
}