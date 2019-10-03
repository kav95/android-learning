package com.example.android.testapplication

import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FragmentActivityToCheckInternet: AppCompatActivity() {
    private var headlessFragment: HeadlessFragmentToCheckInternet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_internet)

        headlessFragment =
            supportFragmentManager.findFragmentByTag(HeadlessFragmentToCheckInternet.TAG) as HeadlessFragmentToCheckInternet?
        if (headlessFragment == null) {
            headlessFragment = HeadlessFragmentToCheckInternet()
            supportFragmentManager.beginTransaction().add(headlessFragment!!, HeadlessFragmentToCheckInternet.TAG)
                .commit()
        }

        this.registerReceiver(NetworkConnectivityReceiver(), IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    override fun onResume() {
        super.onResume()
        val textView = findViewById<TextView>(R.id.check_internet_button)
        textView.setText(HeadlessFragmentToCheckInternet.isInternetConnected(this).toString())
    }

}