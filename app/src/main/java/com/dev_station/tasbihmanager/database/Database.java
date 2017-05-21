package com.dev_station.tasbihmanager.database;

/**
 * Created by Abdullah Shekhar on 5/21/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev_station.tasbihmanager.model.TasbihItem;

import java.util.ArrayList;
import java.util.List;

/*
 * usage:
 * DatabaseSetup.init(egActivityOrContext);
 * DatabaseSetup.createEntry() or DatabaseSetup.getContactNames() or DatabaseSetup.getDb()
 * DatabaseSetup.deactivate() then job done
 */

public class Database extends SQLiteOpenHelper {
    static Database instance = null;
    static SQLiteDatabase database = null;

    static final String DATABASE_NAME = "DB";
    static final int DATABASE_VERSION = 1;

    public static final String ALARM_TABLE = "tasbih";

    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_TOTAL = "total";

    public static void init(Context context) {
        if (null == instance) {
            instance = new Database(context);
        }
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

    public static long create(TasbihItem tasbihItem) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME, tasbihItem.getItemName());
        cv.put(COLUMN_TOTAL, tasbihItem.getTotal());

        return getDatabase().insert(ALARM_TABLE, null, cv);
    }
    public static int update(TasbihItem tasbihItem) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME, tasbihItem.getItemName());
        cv.put(COLUMN_TOTAL, tasbihItem.getTotal());

        return getDatabase().update(ALARM_TABLE, cv, "_id=" + tasbihItem.getItemName(), null);
    }
    public static int deleteEntry(TasbihItem tasbihItem){
        return deleteEntry(tasbihItem.getItemName());
    }

    public static int deleteEntry(String id){
        return getDatabase().delete(ALARM_TABLE, COLUMN_ITEM_NAME + "=" + id, null);
    }

    public static int deleteAll(){
        return getDatabase().delete(ALARM_TABLE, "1", null);
    }

    /*public static Alarm getAlarm(int id) {
        // TODO Auto-generated method stub
        String[] columns = new String[] {
                COLUMN_ALARM_ID,
                COLUMN_ALARM_ACTIVE,
                COLUMN_ALARM_TIME,
                COLUMN_ALARM_DAYS,
                COLUMN_ALARM_DIFFICULTY,
                COLUMN_ALARM_TONE,
                COLUMN_ALARM_VIBRATE,
                COLUMN_ALARM_NAME
        };
        Cursor c = getDatabase().query(ALARM_TABLE, columns, COLUMN_ALARM_ID+"="+id, null, null, null,
                null);
        Alarm alarm = null;

        if(c.moveToFirst()){

            alarm =  new Alarm();
            alarm.setId(c.getInt(1));
            alarm.setAlarmActive(c.getInt(2)==1);
            alarm.setAlarmTime(c.getString(3));
            byte[] repeatDaysBytes = c.getBlob(4);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(repeatDaysBytes);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Alarm.Day[] repeatDays;
                Object object = objectInputStream.readObject();
                if(object instanceof Alarm.Day[]){
                    repeatDays = (Alarm.Day[]) object;
                    alarm.setDays(repeatDays);
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            alarm.setDifficulty(Difficulty.values()[c.getInt(5)]);
            alarm.setAlarmTonePath(c.getString(6));
            alarm.setVibrate(c.getInt(7)==1);
            alarm.setAlarmName(c.getString(8));
        }
        c.close();
        return alarm;
    }*/

    public static Cursor getCursor() {
        // TODO Auto-generated method stub
        String[] columns = new String[] {
                COLUMN_ITEM_NAME,
                COLUMN_TOTAL
        };
        return getDatabase().query(ALARM_TABLE, columns, null, null, null, null,
                null);
    }

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL("CREATE TABLE IF NOT EXISTS " + ALARM_TABLE + " ( "
                + COLUMN_ITEM_NAME + " TEXT primary key, "
                + COLUMN_TOTAL + " INTEGER" + ")"
                );

        db.execSQL("INSERT INTO tasbih VALUES ('Subhan Allah',0)");
        db.execSQL("INSERT INTO tasbih VALUES ('Alhamdulillah',0)");
        db.execSQL("INSERT INTO tasbih VALUES ('Allahu Akbar',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE);
        onCreate(db);
    }

    public static List<TasbihItem> getAll() {
        List<TasbihItem> contactList = new ArrayList<TasbihItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ALARM_TABLE;

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