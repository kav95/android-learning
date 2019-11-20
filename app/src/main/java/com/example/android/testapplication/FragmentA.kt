package com.example.android.testapplication

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class FragmentA: Fragment() {
    internal lateinit var callback: Communicate
    lateinit var inputTextView: EditText

    fun setCallback(callback: Communicate) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_a, container, false)
        inputTextView = view.findViewById(R.id.fragment_a_input_textView)
        return view
    }

    override fun onStart() {
        super.onStart()
        inputTextView.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                onInput()
                true
            } else false
        }
    }

    fun onInput() {
        callback.onEnteringInput(Integer.parseInt(inputTextView.text.toString()))
    }

    interface Communicate {
        fun onEnteringInput(input: Int)
    }
}