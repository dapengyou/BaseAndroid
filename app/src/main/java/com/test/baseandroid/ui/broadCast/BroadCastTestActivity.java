package com.test.baseandroid.ui.broadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.baseandroid.R;

public class BroadCastTestActivity extends AppCompatActivity {
    public static final String TAG = "BroadCast";
    private TextView mTvText;
    private Button mButton;

    private MyReceiver mReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_test);
        mTvText = findViewById(R.id.tv_textview);
        mButton = findViewById(R.id.bt_button);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BroadCastTestActivity.this, BroadCastActivity.class));
            }
        });

        mReceiver = new MyReceiver();

        //注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.test.baseandroid.MY_BROADCAST");

        mLocalBroadcastManager.registerReceiver(mReceiver, filter);//本地广播注册
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.test.baseandroid.MY_BROADCAST")) {
//                mHandler.sendEmptyMessage(1);
                mTvText.setText("接收成功"); //处理界面更新

            }

        }
    }

    //处理
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mTvText.setText("接收成功"); //处理界面更新
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }
}
