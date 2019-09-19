package com.flogiston.test.presentation.extract


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.flogiston.test.R

/**
 * A simple [Fragment] subclass.
 */
class ExtractFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBroadcastReceiver()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_extract, container, false)
    }

    private fun registerBroadcastReceiver(){
        val localBroadcastManager = LocalBroadcastManager.getInstance(context!!)
        val intentFilter = IntentFilter()
        intentFilter.addAction(SHOW_PROGRESS)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                //TODO: handle progress notifications
            }
        },
            intentFilter)
    }

    companion object {
        const val SHOW_PROGRESS = "show_progress"
    }
}
