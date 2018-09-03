package com.test.baseandroid.ui.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.baseandroid.R;
import com.test.baseandroid.ui.base.BaseActivity;
import com.test.baseandroid.utils.binding.Bind;

import java.util.ArrayList;
import java.util.List;

public class SqlLiteActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.bt_add)
    private Button mBtAdd;
    @Bind(R.id.bt_query)
    private Button mBtQuery;
    @Bind(R.id.bt_update)
    private Button mBtUpdate;
    @Bind(R.id.bt_delete)
    private Button mBtDelete;
    @Bind(R.id.tv_textview)
    private TextView mTvTextView;

    private MyDataHelper mMyDataHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);
        initListener();

        mMyDataHelper = new MyDataHelper(this, "music.db", null, 1);
    }

    private void initListener() {
        mBtAdd.setOnClickListener(this);
        mBtDelete.setOnClickListener(this);
        mBtQuery.setOnClickListener(this);
        mBtUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
//                mMyDataHelper.getReadableDatabase();
                addData();
                break;

            case R.id.bt_query:
                queryData();
                break;
            case R.id.bt_update:
                updateData();
                break;
            case R.id.bt_delete:
                deleteData();
                break;
        }
    }

    /**
     * 查询数据
     */
    private void queryData() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = mMyDataHelper.getWritableDatabase();
        Cursor cursor = db.query("music", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //遍历
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String musicName = cursor.getString(cursor.getColumnIndex("musicName"));
                String address = cursor.getString(cursor.getColumnIndex("address"));

                list.add("作者：" + author + "，歌名：" + musicName + "，播放地址：" + address);
            } while (cursor.moveToNext());
            cursor.close();
            mTvTextView.setText(list.toString());
        }
    }

    /**
     * 删除数据
     */
    private void deleteData() {
        SQLiteDatabase db = mMyDataHelper.getWritableDatabase();
        db.delete("music", "author=?", new String[]{"赵雷"});
    }

    /**
     * 更改数据
     */
    private void updateData() {
        SQLiteDatabase db = mMyDataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", "网易云音乐");
        db.update("music", contentValues, "musicName=?", new String[]{"南方姑娘"});
    }

    /**
     * 添加数据
     */
    private void addData() {
        SQLiteDatabase db = mMyDataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author", "赵雷");
        contentValues.put("musicName", "南方姑娘");
        db.insert("music", null, contentValues);
        contentValues.clear();
        //开始写入第二条数据
        contentValues.put("author", "赵雷");
        contentValues.put("musicName", "成都");
        db.insert("music", null, contentValues);
        contentValues.clear();
        //开始写入第三条数据
        contentValues.put("author", "李响");
        contentValues.put("musicName", "飞一般的感觉");
        contentValues.put("address", "网易云音乐");
        db.insert("music", null, contentValues);
    }
}
