package com.example.android.testapplication

import android.os.Bundle
import androidx.fragment.app.Fragment

class HeadlessFragment: Fragment() {
    var list: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}