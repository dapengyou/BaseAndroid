package com.test.baseandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.baseandroid.R;
import com.test.baseandroid.ui.base.BaseActivity;
import com.test.baseandroid.ui.broadCast.BroadCastTestActivity;
import com.test.baseandroid.ui.sqlLite.SqlLiteActivity;
import com.test.baseandroid.utils.binding.Bind;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.bt_broadcast)
    private Button mBtBroadCast;

    @Bind(R.id.bt_sqlLite)
    private Button mBtSqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initClickListener();

    }

    private void initClickListener() {
        mBtBroadCast.setOnClickListener(this);
        mBtSqlLite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_broadcast:
                startActivity(new Intent(this, BroadCastTestActivity.class));
                break;
            case R.id.bt_sqlLite:
                startActivity(new Intent(this, SqlLiteActivity.class));
                break;
        }
    }

}
