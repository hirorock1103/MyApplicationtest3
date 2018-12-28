package com.example.user.myapplicationtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardContentsManager extends MyDbHelper {


    public CardContentsManager(@Nullable Context context) {
        super(context);
    }

    public String[] getHiraganaDataNotExistsOnly(String[] hiragana){

        List<String> notExistList = new ArrayList<>();

        String[] existsList = getHiranagqaDataExistsOnly(hiragana);

        for(int i = 0; i < hiragana.length; i++){
            if(Arrays.asList(existsList).contains(hiragana[i])){
                //if contains
            }else{
                //if not contains
                notExistList.add(hiragana[i]);
            }
        }

        String[] result = notExistList.toArray(new String[notExistList.size()]);

        return result;

    }


    public String[] getHiranagqaDataExistsOnly(String[] hiragana){

        List<String> list = new ArrayList<>();

        for (int i = 0; i < hiragana.length; i++){

            //check   hiragana[0]
            if(this.getCardContentsByFirstLetter(hiragana[i]).size() > 0){

                list.add(hiragana[i]);

            }

        }

        String[] strList = new String[list.size()];

        for (int i = 0; i < list.size(); i++){
            strList[i] = list.get(i);
        }

        return strList;

    }

    public boolean isExistsSameTitle(String title){

        String query = "SELECT * FROM " + TABLE_CARDCONTENTS + " WHERE " + CARDCONTENTS_COLUMN_TITLE + " = '"+title+"'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.getCount() > 0){
            return true;
        }

        return false;
    }


    public int addCardContents(CardContents card){

        ContentValues values = new ContentValues();
        values.put(CARDCONTENTS_COLUMN_TITLE, card.getCard_title());
        values.put(CARDCONTENTS_COLUMN_IMAGE, card.getCard_image());
        //values.put(CARDCONTENTS_COLUMN_INDEX, card.getCard_index());
        //values.put(CARDCONTENTS_COLUMN_CREATEDATE, card.getCreatedate());

        SQLiteDatabase db = getWritableDatabase();
        long returnId = db.insert(TABLE_CARDCONTENTS,null,values);

        return (int)returnId;

    }

    /**
     *
     * @param index
     * @param returnCount
     * @return
     */
    public List<CardContents> getCardContentsByIndex(int index, int returnCount){

        List<CardContents> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        String query = " SELECT * FROM " + TABLE_CARDCONTENTS +
                " WHERE "+ CARDCONTENTS_COLUMN_INDEX + " = " + index;
        if(returnCount > 0 ){
            query += " LIMIT " + returnCount;
        }

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){

            CardContents contents = new CardContents();

            contents.setCard_id(c.getInt(c.getColumnIndex(CARDCONTENTS_COLUMN_ID)));
            contents.setCard_title(c.getString(c.getColumnIndex(CARDCONTENTS_COLUMN_TITLE)));
            contents.setCard_image(c.getBlob(c.getColumnIndex(CARDCONTENTS_COLUMN_IMAGE)));
            contents.setCard_index(c.getInt(c.getColumnIndex(CARDCONTENTS_COLUMN_INDEX)));
            contents.setCreatedate(c.getString(c.getColumnIndex(CARDCONTENTS_COLUMN_CREATEDATE)));

            list.add(contents);

            c.moveToNext();

        }

        return list;

    }

    public void delete(int id){

        String query = "DELETE FROM " + TABLE_CARDCONTENTS + " WHERE " + CARDCONTENTS_COLUMN_ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }


    public List<CardContents> getCardList(){

        String query = "SELECT " +CARDCONTENTS_COLUMN_ID + ", " +CARDCONTENTS_COLUMN_TITLE+ " FROM " + TABLE_CARDCONTENTS + " ORDER BY " + CARDCONTENTS_COLUMN_TITLE + " ASC ";

        List<CardContents> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){

            CardContents contents = new CardContents();

            contents.setCard_id(c.getInt(c.getColumnIndex(CARDCONTENTS_COLUMN_ID)));
            contents.setCard_title(c.getString(c.getColumnIndex(CARDCONTENTS_COLUMN_TITLE)));
            //contents.setCard_image(c.getBlob(c.getColumnIndex(CARDCONTENTS_COLUMN_IMAGE)));

            list.add(contents);

            c.moveToNext();

        }


        return list;


    }

    /**
     *
     * @param hiragana
     * @return
     */
    public List<CardContents> getCardContentsByFirstLetter(String hiragana){

        List<CardContents> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        String query = " SELECT * FROM " + TABLE_CARDCONTENTS +
                " WHERE "+ CARDCONTENTS_COLUMN_TITLE + " like '" + hiragana + "%'";


        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){

            CardContents contents = new CardContents();

            contents.setCard_id(c.getInt(c.getColumnIndex(CARDCONTENTS_COLUMN_ID)));
            contents.setCard_title(c.getString(c.getColumnIndex(CARDCONTENTS_COLUMN_TITLE)));
            contents.setCard_image(c.getBlob(c.getColumnIndex(CARDCONTENTS_COLUMN_IMAGE)));

            list.add(contents);

            c.moveToNext();

        }


        return list;

    }


}
