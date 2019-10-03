package com.example.android.testapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FragmentActivity: AppCompatActivity() {

    private var headlessFragment: HeadlessFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headlessFragment = supportFragmentManager.findFragmentByTag("HEADLESS_FRAGMENT") as HeadlessFragment?
        if (headlessFragment == null) {
            headlessFragment = HeadlessFragment()
            supportFragmentManager.beginTransaction().add(headlessFragment!!, "HEADLESS_FRAGMENT").commit()
        }

        editTextView()
    }

    fun getData(): String {
        if (headlessFragment?.list == null) {
            headlessFragment?.list = listOf("abc", "def", "ghi")
        }

        return headlessFragment!!.list!![0] + headlessFragment!!.list!![1] + headlessFragment!!.list!![2]
    }

    fun editTextView() {
        val textView = this.findViewById<TextView>(R.id.textView)
        textView.setText(getData())
    }
}