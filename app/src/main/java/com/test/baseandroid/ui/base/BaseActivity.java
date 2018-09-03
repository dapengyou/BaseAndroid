package com.test.baseandroid.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.baseandroid.utils.binding.ViewBinder;
import com.test.baseandroid.utils.clickListener.ViewListener;

public abstract class BaseActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
//        initListener();
    }

    private void initView() {
        ViewBinder.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initView();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
    }
    /**
     * 初始化点击事件
     */
//    protected abstract void initListener();

    /**
     * 当View被点击时调用
     *
     * @param v
     */
//    protected abstract void onViewClick(View v);

//    @Override
//    public void onClick(View v) {
//        onViewClick(v);
//    }

    /**
     * 显示传入ResId 的 Toast
     *
     * @param resId
     */
    protected void show(int resId) {
        show(getString(resId), false);
    }

    /**
     * 显示toast
     *
     * @param message 显示的内容
     * @param isLong  是否是长Toast
     */
    protected void show(String message, boolean isLong) {
        if (isLong) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
