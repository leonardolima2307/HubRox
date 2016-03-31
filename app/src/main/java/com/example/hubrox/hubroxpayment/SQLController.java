package com.example.hubrox.hubroxpayment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
    SQLiteDatabase sqLiteDatabase;
    MyDbHelper myDbHelper;
    Context context;

    public SQLController(Context paramContext) {
        this.context = paramContext;
    }

    public void close() {
        this.myDbHelper.close();
    }

    public void deleteItem(Integer paramInteger) {
        this.sqLiteDatabase.delete("items", "_id=?", new String[]{paramInteger.toString()});
    }

    public void insertItemData(String paramString1, String paramString2, float paramFloat) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("code", paramString1);
        localContentValues.put("description", paramString2);
        localContentValues.put("price", Float.valueOf(paramFloat));
        this.sqLiteDatabase.insert("items", null, localContentValues);
    }

    public void insertPaymentData(String paramString, float paramFloat) {
        paramString = "INSERT INTO payments (credit_no, amount, date) VALUES ('" + paramString + "', " + String.format("%f", new Object[]{Float.valueOf(paramFloat)}) + ",DateTime('now'))";
        this.sqLiteDatabase.execSQL(paramString);
    }

    public SQLController open()
            throws SQLException {
        this.myDbHelper = new MyDbHelper(this.context);
        this.sqLiteDatabase = this.myDbHelper.getWritableDatabase();
        return this;
    }

    public Cursor readEntry() {
        Cursor localCursor = this.sqLiteDatabase.query("payments", new String[]{"_id", "credit_no", "amount", "date"}, null, null, null, null, null);
        if (localCursor != null) {
            localCursor.moveToFirst();
        }
        return localCursor;
    }

    public Cursor readItemsByCode(Cursor paramString) {
        paramString = this.sqLiteDatabase.query("items", new String[]{"_id", "code", "description", "price"}, "code=?", new String[]{String.valueOf(paramString)}, null, null, null);
        if (paramString != null) {
            paramString.moveToFirst();
        }
        return paramString;
    }

    public Cursor readItemsEntry() {
        Cursor cursor = this.sqLiteDatabase.query("items", new String[]{"_id", "code", "description", "price"}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void updateItemData(int paramInt, String paramString1, String paramString2, float paramFloat) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("code", paramString1);
        localContentValues.put("description", paramString2);
        localContentValues.put("price", Float.valueOf(paramFloat));
        this.sqLiteDatabase.update("items", localContentValues, "_id=?", new String[]{String.format("%d", new Object[]{Integer.valueOf(paramInt)})});
    }
}