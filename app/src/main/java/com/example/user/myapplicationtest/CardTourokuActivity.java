package com.example.user.myapplicationtest;

import android.content.SharedPreferences;
import android.os.TransactionTooLargeException;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardTourokuActivity extends AppCompatActivity {
    int langSetting;
    String join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_touroku);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        langSetting = sharedPreferences.getInt("LANG", 0);

        Button hiraganaButton = findViewById(R.id.hiragana);
        if(langSetting == 200){
            hiraganaButton.setText("アルファベット");
        }
        hiraganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CardContentsManager cardManager = new CardContentsManager(CardTourokuActivity.this);

                String title;
                String[] list;
                if(langSetting == 200){
                    list = cardManager.getHiraganaDataNotExistsOnly(Common.ALPHABET);
                    title = "未登録のアルファベット";
                }else{
                    list = cardManager.getHiraganaDataNotExistsOnly(Common.HIRANAGQA);
                    title = "未登録のひらがな";
                }

                join = "";
                for(String s : list){
                    join = join + s + ",";
                }
                join = join.substring(0, join.length()-1);


                AlertDialog.Builder builder = new AlertDialog.Builder(CardTourokuActivity.this);
                builder.setTitle(title);
                builder.setMessage(join);
                builder.setPositiveButton("OK",null);
                builder.show();

            }
        });



        //textViewSub.setText(join);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.f_container, new Fragment2());
        transaction.commit();



    }
}
