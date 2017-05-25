package com.dev_station.dhikrmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev_station.dhikrmanager.model.TasbihItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdullah Shekhar on 5/20/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    static DatabaseHandler instance = null;
    static SQLiteDatabase database = null;
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dhikrmanager";

    // Contacts table name
    private static final String TABLE_TASBIH = "tasbih";

    // Contacts Table Columns names
    private static final String KEY_NAME = "item_name";
    private static final String KEY_TOTAL = "total";


    public static void init(Context context) {
        if (null == instance) {
            instance = new DatabaseHandler(context);
        }
    }

    public static DatabaseHandler getInstance(Context context) {
        if (null == instance) {
            instance = new DatabaseHandler(context);
        }
        return instance;
    }

    public static SQLiteDatabase getDatabase() {
        if (null == database) {
            database = instance.getWritableDatabase();
        }
        return database;
    }

    public static void deactivate() {
        if (null != database && database.isOpen()) {
            database.close();
        }
        database = null;
        instance = null;
    }

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
        db.execSQL("INSERT INTO tasbih VALUES ('Subhan Allah',0)");
        db.execSQL("INSERT INTO tasbih VALUES ('Alhamdulillah',0)");
        db.execSQL("INSERT INTO tasbih VALUES ('Allahu Akbar',0)");

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
        values.put(KEY_NAME, tasbihItem.getItemName()); // Item Name
        values.put(KEY_TOTAL, tasbihItem.getTotal()); // Total

        // Inserting Row
        db.insert(TABLE_TASBIH, null, values);
        db.close(); // Closing database connection
    }

    public static List<TasbihItem> getAllContacts() {
        List<TasbihItem> contactList = new ArrayList<TasbihItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASBIH;

        SQLiteDatabase db = getDatabase();
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
