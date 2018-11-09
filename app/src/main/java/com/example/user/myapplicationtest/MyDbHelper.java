package com.example.user.myapplicationtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {


    protected final static int DB_VERSION = 4;
    protected final static String DB_NAME = "KotobaOboe.db";
    protected final static String TABLE_CARDCONTENTS = "CardContents";
    protected final static String CARDCONTENTS_COLUMN_ID = "card_id";
    protected final static String CARDCONTENTS_COLUMN_TITLE = "card_title";
    protected final static String CARDCONTENTS_COLUMN_IMAGE = "card_image";
    protected final static String CARDCONTENTS_COLUMN_INDEX = "card_index";
    protected final static String CARDCONTENTS_COLUMN_CREATEDATE = "createdate";


    public MyDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " +  TABLE_CARDCONTENTS + "(" +
                CARDCONTENTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CARDCONTENTS_COLUMN_TITLE + " TEXT, " +
                CARDCONTENTS_COLUMN_IMAGE + " BLOB, " +
                CARDCONTENTS_COLUMN_INDEX + " INTEGER, " +
                CARDCONTENTS_COLUMN_CREATEDATE + "TEXT" +
                ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query = "DROP TABLE IF EXISTS  " + TABLE_CARDCONTENTS;
        db.execSQL(query);

        onCreate(db);


    }
}
