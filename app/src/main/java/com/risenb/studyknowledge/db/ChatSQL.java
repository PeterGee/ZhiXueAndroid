package com.risenb.studyknowledge.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.risenb.studyknowledge.MyApplication;

public class ChatSQL extends SQLiteOpenHelper {
    /**
     * 文件名
     */
    public static final String FILE = "tablechat.db";

    /**
     * 表名
     */
    public static final String TABLE = "tablelist";

    /**
     * 列名 ID
     */
    public static final String ID = "id";

    /**
     * 列名 itemid
     */
    public static final String ITEMID = "itemid";

    /**
     * 列名 item1
     */
    public static final String ITEM1 = "item1";

    /**
     * 列名 item2
     */
    public static final String ITEM2 = "item2";

    /**
     * 列名 item3
     */
    public static final String ITEM3 = "item3";

    public ChatSQL(Context context) {
        super(context, MyApplication.path + FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + "(" + //
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //
                ITEMID + " VARCHAR," + //ID
                ITEM1 + " VARCHAR," + //
                ITEM2 + " VARCHAR," + //
                ITEM3 + " VARCHAR)");//
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE);
        onCreate(db);
    }
}
