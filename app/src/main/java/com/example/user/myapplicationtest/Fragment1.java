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

import static android.app.Activity.RESULT_OK;

public class Fragment1 extends android.support.v4.app.Fragment {
    int REQUEST_CODE = 1000;
    TextView textView;
    TextView titleAll;
    String titleAllKana;
    ImageView imageView;
    Button speech_recg;
    Button success_btn;

    Fragment1Listner listner;
    int langSetting;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String val = bundle.get("key").toString();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        langSetting = sharedPreferences.getInt("LANG", 0);

        View view = inflater.inflate(R.layout.fragment1, container, false);
        textView = view.findViewById(R.id.f_title);
        titleAll = view.findViewById(R.id.f_title_all);
        imageView = view.findViewById(R.id.card_image);
        success_btn = view.findViewById(R.id.success_btn);
        success_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.getfeedback(1, null);
            }
        });
        speech_recg = view.findViewById(R.id.speech_recg);
        textView.setText(val);

        //speech_recg
        speech_recg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startListening();
            }
        });


        CardContentsManager manager = new CardContentsManager(getContext());
        List<CardContents> list = manager.getCardContentsByFirstLetter(val);

        if(list.size() > 0){

            titleAll.setText(list.get(0).getCard_title());
            titleAllKana = Common.convertHiragana2Katakana(titleAll.getText().toString());
            if(list.get(0).getCard_image() != null){
                Bitmap image = BitmapFactory.decodeByteArray(list.get(0).getCard_image(),0,list.get(0).getCard_image().length);
                imageView.setImageBitmap(image);
            }

        }

        return view;

    }

    private void startListening(){
        int lang = 0;
        if(langSetting == 100){
            lang = 0;
        }else{
            lang = 1;
        }


        // 音声認識の　Intent インスタンス
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        if(lang == 0){
            // 日本語
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.JAPAN.toString() );
        }
        else if(lang == 1){
            // 英語
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString() );
        }
        else if(lang == 2){
            // Off line mode
            intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
        }
        else{
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        }

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "音声を入力");

        try {
            // インテント発行
            startActivityForResult(intent, REQUEST_CODE);
        }
        catch (ActivityNotFoundException e) {
            e.printStackTrace();
            textView.setText("Error");
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // 認識結果を ArrayList で取得
            ArrayList<String> candidates =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if(candidates.size() > 0) {
                boolean exists = false;
                for (String str : candidates){

                    if(langSetting == 200){
                        if(str.toUpperCase().equals(titleAll.getText().toString().toUpperCase())){
                            exists = true;
                            break;
                        }
                    }else{
                        if(str.equals(titleAllKana) ||  str.equals(titleAll.getText().toString())){
                            exists = true;
                            break;
                        }
                    }

                }
                // 認識結果候補で一番有力なものを表示
                //textView.setText( candidates.get(0));
                if(exists == true){
                    //textView.setText("正解！");
                    listner.getfeedback(1, candidates);
                }else{
                    //textView.setText("不正解！");
                    listner.getfeedback(0, candidates);
                }
            }
        }
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
