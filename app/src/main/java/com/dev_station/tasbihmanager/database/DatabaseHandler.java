package com.dev_station.tasbihmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev_station.tasbihmanager.model.TasbihItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdullah Shekhar on 5/20/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tasbihmanager";

    // Contacts table name
    private static final String TABLE_TASBIH = "tasbih";

    // Contacts Table Columns names
    private static final String KEY_NAME = "item_name";
    private static final String KEY_TOTAL = "total";




    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASBIH_TABLE = "CREATE TABLE " + TABLE_TASBIH + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_TOTAL + " INTEGER" + ")";
        db.execSQL(CREATE_TASBIH_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASBIH);

        // Create tables again
        onCreate(db);
    }

    // Adding new item
   public void addTasbihItem(TasbihItem tasbihItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, tasbihItem.getItemName()); // Contact Name
        values.put(KEY_TOTAL, tasbihItem.getTotal()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_TASBIH, null, values);
        db.close(); // Closing database connection
    }

    public List<TasbihItem> getAllContacts() {
        List<TasbihItem> contactList = new ArrayList<TasbihItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASBIH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TasbihItem tasbih = new TasbihItem();

                tasbih.setItemName(cursor.getString(0));
                tasbih.setTotal(Integer.parseInt(cursor.getString(1)));
                // Adding contact to list
                contactList.add(tasbih);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}
