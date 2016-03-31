package com.example.hubrox.hubroxpayment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper
        extends SQLiteOpenHelper {
    private static final String CREATE_PAYMENTS_TABLE = "create table payments(_id INTEGER PRIMARY KEY AUTOINCREMENT, credit_no TEXT NOT NULL ,amount REAL NOT NULL ,date DATETIME);";
    private static final String CREATE_PRODUCTS_TABLE = "create table items(_id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT NOT NULL ,description TEXT NOT NULL ,price REAL NOT NULL);";
    static final String DB_NAME = "hubrox.db";
    static final int DB_VERSION = 2;
    public static final String ITEM_CODE = "code";
    public static final String ITEM_DESCRIPTION = "description";
    public static final String ITEM_ID = "_id";
    public static final String ITEM_PRICE = "price";
    public static final String PAYMENT_AMOUNT = "amount";
    public static final String PAYMENT_CREDIT_NO = "credit_no";
    public static final String PAYMENT_DATE = "date";
    public static final String PAYMENT_ID = "_id";
    public static final String TABLE_ITEMS = "items";
    public static final String TABLE_PAYMENTS = "payments";

    public MyDbHelper(Context paramContext) {
        super(paramContext, "hubrox.db", null, 2);
    }

    public void insertToPayments(SQLiteDatabase paramSQLiteDatabase, String paramString, float paramFloat) {
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        paramSQLiteDatabase.execSQL("create table payments(_id INTEGER PRIMARY KEY AUTOINCREMENT, credit_no TEXT NOT NULL ,amount REAL NOT NULL ,date DATETIME);");
        paramSQLiteDatabase.execSQL("create table items(_id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT NOT NULL ,description TEXT NOT NULL ,price REAL NOT NULL);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS payments");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS items");
        onCreate(paramSQLiteDatabase);
    }
}