package com.test.baseandroid.ui.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.baseandroid.R

class BroadActivity : AppCompatActivity() {

    private var netWorkChangeReceiver: NetWorkChangeReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentFilter = IntentFilter()
        intentFilter!!.addAction("android.net.conn.CONNECTIVITY_CHANGE")

        netWorkChangeReceiver = NetWorkChangeReceiver()
        registerReceiver(netWorkChangeReceiver, intentFilter)
    }

    class NetWorkChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(netWorkChangeReceiver)
    }
}
