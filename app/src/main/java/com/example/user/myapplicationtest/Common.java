package com.example.user.myapplicationtest;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {

    public final static String[] HIRANAGQA = {
            "あ","い","う","え","お",
            "か","き","く","け","こ",
            "さ","し","す","せ","そ",
            "た","ち","つ","て","と",
            "な","に","ぬ","ね","の",
            "は","ひ","ふ","へ","ほ",
            "ま","み","む","め","も",
            "ら","り","る","れ","ろ",
            "や","ゆ","よ","ん",
            "が","ぎ","ぐ","げ","ご",
            "ざ","じ","ず","ぜ","ぞ",
            "だ","ぢ","づ","で","ど",
            "ば","び","ぶ","べ","ぼ"};

    public final static String[] ALPHABET = {
            "A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N",
            "O","P","Q","R","S","T","U",
            "V","W","X","Y","Z",
    };


    public final static String dateFormatBasic = "yyyy/MM/dd HH:mm:ss";


    public static String GetFormatDateBy(String format, int dateDifference){

        String formatedDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {

            Date nextDate = sdf.parse(format);
            Calendar nextCal = Calendar.getInstance();
            nextCal.setTime(new Date());
            nextCal.add(Calendar.DAY_OF_MONTH, 1);
            nextDate = nextCal.getTime();
            formatedDate = nextDate.toString();
            
        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("INFO", e.getMessage());
        }

        return formatedDate;
    }

    public static String convertHiragana2Katakana(String str) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char code = str.charAt(i);
            if ((code >= 0x3041) && (code <= 0x3093)) {
                buf.append((char) (code + 0x60));
            } else {
                buf.append(code);
            }
        }
        return buf.toString();
    }


}
