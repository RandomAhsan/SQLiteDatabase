package com.bongoacademy.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "moneybag", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");
        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists expense");
        db.execSQL("drop table if exists income");
    }

    public void addExpense (double amount, String reason) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues conVal  = new ContentValues();
        conVal.put("amount", amount);
        conVal.put("reason", reason);
        conVal.put("time", System.currentTimeMillis());

        database.insert("expense", null, conVal);
    }

    public void addIncome (double amount, String reason) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues conVal  = new ContentValues();
        conVal.put("amount", amount);
        conVal.put("reason", reason);
        conVal.put("time", System.currentTimeMillis());

        database.insert("income", null, conVal);
    }


    public double calculateTotalExpense () {
        double totalExpense = 0 ;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor =  database.rawQuery("select * from expense", null);


        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                double amount = cursor.getDouble(1);
                totalExpense = totalExpense+amount ;
            }
        }

        return totalExpense;

    }

    public double calculateTotalIncome () {
        double total = 0 ;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor =  database.rawQuery("select * from income", null);


        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                double amount = cursor.getDouble(1);
                total = total+amount ;
            }
        }

        return total;

    }


    public Cursor getAllExpenses () {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from expense",null );


        return cursor ;
    }


    public Cursor getAllIncome () {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from income",null );


        return cursor ;
    }



    public void deleteExpense (String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from expense where id like "+id);

    }


    public void deleteIncome (String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from income where id like "+id);

    }



}
