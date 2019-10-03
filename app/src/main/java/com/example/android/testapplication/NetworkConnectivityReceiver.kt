@file:Suppress("DEPRECATION")

package com.example.android.testapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NetworkConnectivityReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val intentNew = Intent(HeadlessFragmentToCheckInternet.CHECK_INTERNET)
        intentNew.putExtra(
            HeadlessFragmentToCheckInternet.CHECK_INTERNET,
            HeadlessFragmentToCheckInternet.isInternetConnected(context)
        )
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(intentNew)
    }
}