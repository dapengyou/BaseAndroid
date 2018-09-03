package com.test.baseandroid.ui.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataHelper extends SQLiteOpenHelper {
    public static final String CREATE_MUSIC = "create table music(" +
            "id integer primary key autoincrement," +
            "author text," +
            "musicName text," +
            "address text)";

    private Context mContext;

    public MyDataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MUSIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists music");
        onCreate(db);
    }
}
