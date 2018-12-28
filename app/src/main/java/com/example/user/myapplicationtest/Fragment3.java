package com.example.user.myapplicationtest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.app.Activity.RESULT_OK;



public class Fragment3 extends android.support.v4.app.Fragment {
    int REQUEST_CODE = 1000;
    TextView textView;
    TextView titleAll;
    String titleAllKana;
    ImageView imageView;
    Button bt_delete;
    Button bt_edit;

    Fragment1Listner listner;
    int langSetting;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String val = bundle.get("key").toString();//ひらがな

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        langSetting = sharedPreferences.getInt("LANG", 0);

        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        textView = view.findViewById(R.id.f_title);
        titleAll = view.findViewById(R.id.f_title_all);
        imageView = view.findViewById(R.id.card_image);
        textView.setText(val);


        CardContentsManager manager = new CardContentsManager(getContext());
        List<CardContents> list = manager.getCardContentsByFirstLetter(val);

        if(list.size() > 0){

            Random rand = new Random();
            int num = rand.nextInt(list.size());

            titleAll.setText(list.get(num).getCard_title());
            titleAllKana = Common.convertHiragana2Katakana(titleAll.getText().toString());
            if(list.get(num).getCard_image() != null){
                Bitmap image = BitmapFactory.decodeByteArray(list.get(num).getCard_image(),0,list.get(num).getCard_image().length);
                imageView.setImageBitmap(image);
            }

            bt_edit = view.findViewById(R.id.bt_edit);
            bt_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });
            bt_delete = view.findViewById(R.id.btn_delete);
            //speech_recg
            bt_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });


        }



        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listner = (Fragment1Listner)context;
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public interface Fragment1Listner{
        public void getfeedback(int result, ArrayList<String> candidates);
    }
}
