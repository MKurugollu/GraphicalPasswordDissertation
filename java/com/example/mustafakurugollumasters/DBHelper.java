package com.example.mustafakurugollumasters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 11);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table graphicalpassword1(username TEXT primary key, gp1 INTEGER, gp2 INTEGER, gp3 INTEGER, gp4 INTEGER)");
        MyDB.execSQL("create Table graphicalpassword2(username TEXT primary key, gp1 INTEGER, gp2 INTEGER, gp3 INTEGER, gp4 INTEGER, gp5 INTEGER)");
        MyDB.execSQL("create Table graphicalpassword3(username TEXT primary key, gp1, INTEGER, gp2 INTEGER, gp3 INTEGER, gp4 INTEGER, gp5 INTEGER, start INTEGER, finish INTEGER)");
        MyDB.execSQL("create Table graphicalpassword4(username TEXT primary key, gp1, INTEGER, gp2 INTEGER, gp3 INTEGER, gp4 INTEGER, gp5 INTEGER, ind1 INTEGER, ind2 INTEGER, ind3 INTEGER, ind4 INTEGER, ind5 INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists graphicalpassword1");
        MyDB.execSQL("drop Table if exists graphicalpassword2");
        MyDB.execSQL("drop Table if exists graphicalpassword3");
        MyDB.execSQL("drop Table if exists graphicalpassword4");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, int gp1, int gp2, int gp3, int gp4){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("gp1", gp1);
        contentValues.put("gp2", gp2);
        contentValues.put("gp3", gp3);
        contentValues.put("gp4", gp4);
        long result = MyDB.insert("graphicalpassword1", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean insertData2(String username, int gp1, int gp2, int gp3, int gp4, int gp5){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("gp1", gp1);
        contentValues.put("gp2", gp2);
        contentValues.put("gp3", gp3);
        contentValues.put("gp4", gp4);
        contentValues.put("gp5", gp5);
        long result = MyDB.insert("graphicalpassword2", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean insertData3(String username, int gp1, int gp2, int gp3, int gp4, int gp5, int start, int finish){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("gp1", gp1);
        contentValues.put("gp2", gp2);
        contentValues.put("gp3", gp3);
        contentValues.put("gp4", gp4);
        contentValues.put("gp5", gp5);
        contentValues.put("start", start);
        contentValues.put("finish", finish);
        long result = MyDB.insert("graphicalpassword3", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean insertData4(String username, int gp1, int gp2, int gp3, int gp4, int gp5, int ind1, int ind2, int ind3, int ind4, int ind5){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("gp1", gp1);
        contentValues.put("gp2", gp2);
        contentValues.put("gp3", gp3);
        contentValues.put("gp4", gp4);
        contentValues.put("gp5", gp5);
        contentValues.put("ind1", ind1);
        contentValues.put("ind2", ind2);
        contentValues.put("ind3", ind3);
        contentValues.put("ind4", ind4);
        contentValues.put("ind5", ind5);
        long result = MyDB.insert("graphicalpassword4", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword1 where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusername2(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword2 where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusername3(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword3 where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusername4(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword4 where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamePassword(String username, int gp1, int gp2, int gp3, int gp4){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword1 where username = ? and gp1 = ? and gp2 = ? and gp3 = ? and gp4 = ?", new String[] {username, Integer.toString(gp1), Integer.toString(gp2), Integer.toString(gp3), Integer.toString(gp4) });
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamePassword3(String username, int gp1, int gp2, int gp3, int gp4, int gp5, int start, int finish){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword1 where username = ? and gp1 = ? and gp2 = ? and gp3 = ? and gp4 = ? and gp5 = ? and start = ? and finish = ?", new String[] {username, Integer.toString(gp1), Integer.toString(gp2), Integer.toString(gp3), Integer.toString(gp4), Integer.toString(gp5), Integer.toString(start), Integer.toString(finish) });
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public ArrayList<Integer> getGP2Items (String username){
        ArrayList<Integer> items = new ArrayList<Integer>();

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword2 where username = ?", new String[] {username});
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                items.add(cursor.getInt(cursor.getColumnIndex("gp1")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp2")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp3")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp4")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp5")));
            }
        }
        return items;
    }

    public ArrayList<Integer> getGP3Items (String username){
        ArrayList<Integer> items = new ArrayList<Integer>();

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword3 where username = ?", new String[] {username});
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                items.add(cursor.getInt(cursor.getColumnIndex("gp1")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp2")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp3")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp4")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp5")));
                items.add(cursor.getInt(cursor.getColumnIndex("start")));
                items.add(cursor.getInt(cursor.getColumnIndex("finish")));
            }
        }
        return items;
    }

    public ArrayList<Integer> getGP4PIcons (String username){
        ArrayList<Integer> items = new ArrayList<Integer>();

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword4 where username = ?", new String[] {username});
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                items.add(cursor.getInt(cursor.getColumnIndex("gp1")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp2")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp3")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp4")));
                items.add(cursor.getInt(cursor.getColumnIndex("gp5")));
            }
        }
        return items;
    }

    public ArrayList<Integer> getGP4Indicators (String username){
        ArrayList<Integer> items = new ArrayList<Integer>();

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from graphicalpassword4 where username = ?", new String[] {username});
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                items.add(cursor.getInt(cursor.getColumnIndex("ind1")));
                items.add(cursor.getInt(cursor.getColumnIndex("ind2")));
                items.add(cursor.getInt(cursor.getColumnIndex("ind3")));
                items.add(cursor.getInt(cursor.getColumnIndex("ind4")));
                items.add(cursor.getInt(cursor.getColumnIndex("ind5")));
            }
        }
        return items;
    }


}
