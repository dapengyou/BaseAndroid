package com.test.baseandroid.ui.broadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.baseandroid.R;

public class BroadCastActivity extends AppCompatActivity {

    private NetWorkChangeReceiver mNetWorkChangeReceiver;

    private IntentFilter mIntentFilter;
    private Button mButton;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        mButton = findViewById(R.id.bt_button);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //本地广播
                Intent intent = new Intent();
                intent.setAction("com.test.baseandroid.MY_BROADCAST");
                mLocalBroadcastManager.sendBroadcast(intent);
                finish();
            }
        });

        //系统广播
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        mNetWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(mNetWorkChangeReceiver, mIntentFilter);
    }

    public class NetWorkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetWorkChangeReceiver);
    }
}
