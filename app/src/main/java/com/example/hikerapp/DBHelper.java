package com.example.hikerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //db version
    private static final int DATABASE_VERSION = 1;
    //db name
    private static final String DATABASE_NAME = "log_database";
    // db table name
    private static final String TABLE_NAME = "LOG";
    //table column
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOCATION = "location";
    public static final String DATE = "date";
    public static final String PARKING = "parking";
    public static final String LENGTH = "length";
    public static final String LEVEL = "level";
    public static final String DESCRIPTION = "description";
    private SQLiteDatabase sqLiteDatabase;

    //create table query
    private static final  String CREATE_TABLE = "create table " + TABLE_NAME + "("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " TEXT NOT NULL," + LOCATION + " TEXT NOT NULL," + DATE + " TEXT NOT NULL," + PARKING + " TEXT NOT NULL," + LENGTH + " TEXT NOT NULL," +
            LEVEL + " TEXT NOT NULL," + DESCRIPTION + " TEXT NOT NULL);";

    //Constructor
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    //add log data
    public void addLogHiking(LogModel logModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, logModel.getName());
        contentValues.put(DBHelper.LOCATION, logModel.getLocation());
        contentValues.put(DBHelper.DATE, logModel.getDate());
        contentValues.put(DBHelper.PARKING, logModel.getParking());
        contentValues.put(DBHelper.LENGTH, logModel.getLength());
        contentValues.put(DBHelper.LEVEL, logModel.getLevel());
        contentValues.put(DBHelper.DESCRIPTION, logModel.getDesc());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    //create getLogList
    public List<LogModel>getLogList(){
        String sql = " select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<LogModel>storeLog = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                String date = cursor.getString(3);
                String parking = cursor.getString(4);
                String length = cursor.getString(5);
                String level = cursor.getString(6);
                String description = cursor.getString(7);
                storeLog.add(new LogModel(id, name, location, date, parking, length, level, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeLog;
    }

    //create update method
    public void updateLog(LogModel logModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME,logModel.getName());
        contentValues.put(DBHelper.LOCATION,logModel.getLocation());
        contentValues.put(DBHelper.DATE,logModel.getDate());
        contentValues.put(DBHelper.PARKING,logModel.getParking());
        contentValues.put(DBHelper.LENGTH,logModel.getLength());
        contentValues.put(DBHelper.LEVEL,logModel.getLevel());
        contentValues.put(DBHelper.DESCRIPTION,logModel.getDesc());

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]
                {String.valueOf(logModel.getId())});
    }

    //create delete method
    public void deleteLog(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]
                {String.valueOf(id)});
    }
}
