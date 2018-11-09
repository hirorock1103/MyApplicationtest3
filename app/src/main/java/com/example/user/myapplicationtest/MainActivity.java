package com.example.user.myapplicationtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int lang = sharedPreferences.getInt("LANG", 0);
        if(lang == 0){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("LANG", 100);//100 japanese 200 english
            editor.commit();
        }


        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActicvity.class);
                startActivity(intent);
            }
        });

        Button bt_touroku = findViewById(R.id.bt_touroku);
        bt_touroku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CardTourokuActivity.class);
                startActivity(intent);

            }
        });

        Button bt_switch = findViewById(R.id.bt_switch);
        bt_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                int lang = sharedPreferences.getInt("LANG", 0);
                if(lang == 200){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("LANG", 100);//100 japanese 200 english
                    editor.commit();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("LANG", 200);//100 japanese 200 english
                    editor.commit();
                }

                Toast.makeText(MainActivity.this, "lang : " + lang, Toast.LENGTH_SHORT).show();


            }
        });
    }
}
