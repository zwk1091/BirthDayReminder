package com.example.wenkun.birthdayreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 焜 on 2017/12/6.
 */

public class PeopleDB extends SQLiteOpenHelper {
    private static final String DB_NAME="Contacts.db";
    private static final String TABLE_NAME="Contacts";
    private static final int DB_VERSION=1;
    public PeopleDB(Context c) {
        super(c,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="create table "+TABLE_NAME+"(_id integer primary key,"
                +"name text ,"
                +" birth text ,"
                +" gift text);";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {

    }
    public void update(String name,String birth,String gift) {
        SQLiteDatabase db=getWritableDatabase();
        String whereClause="name = ?";
        String[] whereArgs={ name };
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("birth",birth);
        values.put("gift",gift);
        db.update(TABLE_NAME,values,whereClause,whereArgs);
        db.close();
    }
    public void insert(String name,String birth,String gift) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("birth",birth);
        values.put("gift",gift);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public void delete(String name) {
        SQLiteDatabase db=getWritableDatabase();
        String whereClause="name = ?";
        String[] whereArgs={name};
        db.delete(TABLE_NAME,whereClause,whereArgs);
        db.close();
    }
    public boolean isExistence(String name) {
        SQLiteDatabase db=getWritableDatabase();
        String whereClause="name = ?";
        String[] whereArgs={name};
        Cursor cursor=db.query(TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        if(cursor.getCount()==0) {
            return false;
        } else {
            Log.e("isExistence",Integer.toString(cursor.getCount()));
            cursor.moveToFirst();
            int nameColumn = cursor.getColumnIndex("name");
            int birthColumn = cursor.getColumnIndex("birth");
            int giftColumn=cursor.getColumnIndex("gift");
            String tname = cursor.getString(nameColumn);
            String birth = cursor.getString(birthColumn);
            String gift=cursor.getString(giftColumn);
            Log.e("isExistence",tname);
            Log.e("isExistence",birth);
            Log.e("isExistence",gift);
            return true;
        }
    }
    public Cursor findAllData() {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{"name","birth","gift"},null,null,null,null,null);
        return cursor;
    }
}
