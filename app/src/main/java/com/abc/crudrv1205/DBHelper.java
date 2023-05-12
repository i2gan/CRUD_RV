package com.abc.crudrv1205;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "crud";
    public static final String DB_NAME = TABLE_NAME + "1215.db";
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_YEAR = "year";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_YEAR + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void addOne(Data data) {
        LinkedList<Data> l = getAll();
        if (l.size() == 0) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(COL_NAME, data.name);
            cv.put(COL_YEAR, data.year);

            db.insert(TABLE_NAME, null, cv);
        }
    }

    public LinkedList<Data> getAll() {
        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null,
                null, null, null);

        if (cursor.moveToFirst())
            do {
                int id_n = cursor.getColumnIndex(COL_NAME);
                int id_y = cursor.getColumnIndex(COL_YEAR);

                Data data = new Data(cursor.getString(id_n), cursor.getInt(id_y));
                list.add(data);
            } while (cursor.moveToNext());
        db.close();
        System.out.println("        " + list.size());
        return list;
    }
}
