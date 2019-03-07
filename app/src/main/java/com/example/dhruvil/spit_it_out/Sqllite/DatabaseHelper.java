package com.example.dhruvil.spit_it_out.Sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dhruvil.spit_it_out.Models.MyDBModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spit.db";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table " + MyDBModel.TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Number TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyDBModel.TABLE_NAME);
        onCreate(db);
    }

    public long insertGroup(String name, String members) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDBModel.COULMN_NAME, name);
        values.put(MyDBModel.COULMN_NUMBER, members);

        long id = db.insert(MyDBModel.TABLE_NAME, null, values);
        db.close();
        return id;

    }

    public MyDBModel getgroup(long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MyDBModel.TABLE_NAME, new String[]{
                MyDBModel.COULUMN_ID, MyDBModel.COULMN_NAME, MyDBModel.COULMN_NUMBER
        }, MyDBModel.COULUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {

            cursor.moveToFirst();

        }
        MyDBModel myDBModel = new MyDBModel(
                cursor.getInt(cursor.getColumnIndex(MyDBModel.COULUMN_ID)),
                cursor.getString(cursor.getColumnIndex(MyDBModel.COULMN_NUMBER)),
                cursor.getString(cursor.getColumnIndex(MyDBModel.COULMN_NAME)));
        cursor.close();
        return myDBModel;
    }

    public List<MyDBModel> getAllgroups() {
        List<MyDBModel> myDBModels = new ArrayList<>();


        String selectQuery = "select * from " + MyDBModel.TABLE_NAME + " ORDER BY " + MyDBModel.COULUMN_ID;

        SQLiteDatabase db = DatabaseHelper.this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            do {

                MyDBModel myDBModel = new MyDBModel();
                myDBModel.setId(cursor.getInt(cursor.getColumnIndex(MyDBModel.COULUMN_ID)));
                myDBModel.setName(cursor.getString(cursor.getColumnIndex(MyDBModel.COULMN_NAME)));
                myDBModel.setNumber(cursor.getString(cursor.getColumnIndex(MyDBModel.COULMN_NUMBER)));

                myDBModels.add(myDBModel);
            } while (cursor.moveToNext());
        }
        db.close();
        return myDBModels;
    }

    public int getgroupscount() {

        String countquery = "SELECT  * FROM " + MyDBModel.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countquery, null);

        int count = cursor.getCount();
        cursor.close();
        return count;

    }

    public int updategroup(MyDBModel myDBModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDBModel.COULMN_NAME, myDBModel.getName());
        values.put(MyDBModel.COULMN_NUMBER, myDBModel.getNumber());

        return db.update(MyDBModel.TABLE_NAME, values, MyDBModel.COULUMN_ID + "=?",
                new String[]{
                        String.valueOf(myDBModel.getId())
                });
    }

    public void deletegroup(MyDBModel myDBModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MyDBModel.TABLE_NAME, MyDBModel.COULUMN_ID + "=?", new String[]{
                String.valueOf(myDBModel.getId())
        });
        db.close();
    }


}
