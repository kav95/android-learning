package com.example.android.testapplication

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import android.util.Log
import java.lang.ref.WeakReference


class MainActivity : Activity() {
    val editText by lazy {
        this.findViewById<TextView>(R.id.textView)
    }
//    val button by lazy {
//        this.findViewById<Button>(R.id.sleepButton)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        button.setOnClickListener {
            val sleepTime = editText.text.toString()
            AsyncRunner(this).execute(sleepTime)
//        }
    }

    private inner class AsyncRunner(): AsyncTask<String, String, String>() {
        //        lateinit var progressDialog: ProgressBar
        private lateinit var mainActivity: WeakReference<MainActivity>

        constructor(context: MainActivity) : this() {
            mainActivity = WeakReference(context)
        }

        override fun doInBackground(vararg params: String?): String {
            val time = Integer.parseInt(params[0]!!) * 1000

            for (i in 1..10) {
                Log.d("sleepTime", "Slept for " + params[0] + " seconds " + i.toString())
                Thread.sleep(3000)
            }

            return time.toString()
        }

        override fun onProgressUpdate(vararg values: String?) {

        }

        override fun onPostExecute(result: String?) {
//            progressDialog.progress = 0
//            progressDialog.visibility = View.GONE
//            editText.setText(result)
        }

        override fun onPreExecute() {
//            progressDialog = ProgressBar(context, null, R.attr.progressBarStyle)
//            progressDialog.progress = editText.text.toString().toInt()
        }

        override fun onCancelled() {
            Log.d("cancel", "is cancelled")
        }
    }
}
