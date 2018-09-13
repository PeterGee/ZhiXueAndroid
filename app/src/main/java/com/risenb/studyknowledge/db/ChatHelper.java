package com.risenb.studyknowledge.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.risenb.studyknowledge.beans.CommodityBean;

import java.util.ArrayList;
import java.util.List;

public class ChatHelper {
    private Context context;

    private static ChatHelper chatHelper;

    public static ChatHelper getChatHelper(Context context) {
        if (chatHelper == null) {
            chatHelper = new ChatHelper(context);
        }
        return chatHelper;
    }

    public ChatHelper(Context context) {
        this.context = context;
    }

    public void insert(List<CommodityBean> list) {
        ChatSQL chatSQL = new ChatSQL(context);
        // 获得数据库对象
        SQLiteDatabase db = chatSQL.getWritableDatabase();
        for (CommodityBean bean : list) {
            ContentValues values = new ContentValues();
            values.put(ChatSQL.ITEMID, bean.getId());
            values.put(ChatSQL.ITEM1, bean.getItem1());
            values.put(ChatSQL.ITEM2, bean.getItem2());
            values.put(ChatSQL.ITEM3, bean.getItem3());
            db.insert(ChatSQL.TABLE, ChatSQL.ID, values);
        }
        db.close();
    }

    // 从数据库中查询数据
    public List<CommodityBean> queryData(String item1, String item2, String item3) {
        CommodityBean bean;
        List<CommodityBean> list = new ArrayList<>();
        ChatSQL chatSQL = new ChatSQL(context);
        // 获得数据库对象
        SQLiteDatabase db = chatSQL.getReadableDatabase();

        String selection = "";
        List<String> seltList = new ArrayList();
        if (!TextUtils.isEmpty(item1)) {
            selection += ChatSQL.ITEM1 + " = ? and ";
            seltList.add(item1);
        }
        if (!TextUtils.isEmpty(item2)) {
            selection += ChatSQL.ITEM2 + " = ? and ";
            seltList.add(item2);
        }
        if (!TextUtils.isEmpty(item3)) {
            selection += ChatSQL.ITEM3 + " = ? and ";
            seltList.add(item3);
        }
        if (!TextUtils.isEmpty(selection)) {
            selection = selection.substring(0, selection.length() - 4);
        }

        String[] selectionArgs = new String[seltList.size()];
        for (int i = 0; i < selectionArgs.length; i++) {
            selectionArgs[i] = seltList.get(i);
        }

        // 查询表中的数据
        Cursor cursor = db.query(ChatSQL.TABLE, null, selection, selectionArgs, null, null, ChatSQL.ID + " asc");

        int itemId = cursor.getColumnIndex(ChatSQL.ITEMID);
        int item1Num = cursor.getColumnIndex(ChatSQL.ITEM1);
        int item2Num = cursor.getColumnIndex(ChatSQL.ITEM2);
        int item3Num = cursor.getColumnIndex(ChatSQL.ITEM3);

        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            bean = new CommodityBean();
            bean.setId(cursor.getString(itemId));
            bean.setItem1(cursor.getString(item1Num));
            bean.setItem2(cursor.getString(item2Num));
            bean.setItem3(cursor.getString(item3Num));
            list.add(bean);
        }
        cursor.close();// 关闭结果集
        db.close();// 关闭数据库对象
        return list;
    }

    /**
     * 删除
     */
    public void delete() {
        ChatSQL chatSQL = new ChatSQL(context);
        SQLiteDatabase db = chatSQL.getReadableDatabase();
        db.delete(ChatSQL.TABLE, null, null);
        db.close();
    }
}
