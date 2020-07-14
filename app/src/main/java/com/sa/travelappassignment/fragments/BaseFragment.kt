package com.sa.travelappassignment.fragments

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    fun showSnackbar(message: String){
        Snackbar.make(activity!!.window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }
}