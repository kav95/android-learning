package com.example.android.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityWithFragment: AppCompatActivity(), FragmentA.Communicate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragmentA = FragmentA()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(fragmentA, "FRAGMENT_A").commitNow()
        }

        setContentView(R.layout.activity_with_fragment)
    }

    override fun onEnteringInput(input: Int) {
        var fragmentB = supportFragmentManager.findFragmentById(R.id.fragment_b) as FragmentB?

        if (fragmentB == null) {
            fragmentB = FragmentB()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(fragmentB, "FRAGMENT_B").commitNow()
        }
        fragmentB.output.text = input.toString()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        if (fragment is FragmentA) {
            fragment.setCallback(this)
        }
    }
}