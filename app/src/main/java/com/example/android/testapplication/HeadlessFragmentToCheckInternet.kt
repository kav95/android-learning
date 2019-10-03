@file:Suppress("DEPRECATION")

package com.example.android.testapplication

import android.content.*
import android.net.ConnectivityManager
import android.os.Bundle
import android.content.Context.CONNECTIVITY_SERVICE
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class HeadlessFragmentToCheckInternet: Fragment() {
    var activityReference: FragmentActivityToCheckInternet? = null
    var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private val broadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.hasExtra(Companion.CHECK_INTERNET)!! && !intent.getBooleanExtra(CHECK_INTERNET, true)) {
                showAlertDialog(context, "Network Issue", "Check your internet connectivity")
            } else {
                if (alertDialog != null && alertDialog!!.isShowing) {
                    alertDialog!!.dismiss()
                    alertDialog = null
                }
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityReference = context as FragmentActivityToCheckInternet
    }

    override fun onResume() {
        super.onResume()
        val internetIntentFilter = IntentFilter(CHECK_INTERNET)
        LocalBroadcastManager.getInstance(activityReference!!).registerReceiver(broadcastReceiver, internetIntentFilter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(activityReference!!).unregisterReceiver(broadcastReceiver)
    }

    override fun onDetach() {
        super.onDetach()
        activityReference = null
    }

    fun showAlertDialog(context: Context?, title: String, message: String) {
        if (alertDialog != null && alertDialog!!.isShowing()) {
            return  //already showing
        } else if (alertDialog != null) {
            alertDialog!!.dismiss()
            alertDialog = null
        }
        alertDialog = AlertDialog.Builder(ContextThemeWrapper(activityReference, R.style.Theme_AppCompat)).create()
        alertDialog!!.setTitle(title)
        alertDialog!!.setMessage(message)
        alertDialog!!.setButton(DialogInterface.BUTTON_POSITIVE,
            getString(android.R.string.ok)
        ) { dialog, which ->
            dialog.dismiss()
        }
        alertDialog!!.show()
    }

    companion object {
        val TAG = "NetworkHelper"
        val CHECK_INTERNET = "network_connection"

        fun isInternetConnected(context: Context?): Boolean {
            val connection = context!!.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connection.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

}