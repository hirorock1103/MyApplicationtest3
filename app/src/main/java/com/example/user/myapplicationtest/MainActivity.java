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

    private Button bt_switch_ja;
    private Button bt_switch_en;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int lang = sharedPreferences.getInt("LANG", 0);
        if(lang == 0){
            //何もセットされていない=初期値のケース
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("LANG", 100);//100 japanese 200 english
            editor.commit();
        }

        bt_switch_ja = findViewById(R.id.bt_switch_ja);
        bt_switch_ja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //何もセットされていない=初期値のケース
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("LANG", 100);//100 japanese 200 english
                editor.commit();
            }
        });

        bt_switch_en = findViewById(R.id.bt_switch_en);
        bt_switch_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //何もセットされていない=初期値のケース
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("LANG", 200);//100 japanese 200 english
                editor.commit();
            }
        });


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

        Button bt_edit = findViewById(R.id.bt_edit);
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CardEditActivity.class);
                startActivity(intent);
            }
        });


        Button bt_edit_2 = findViewById(R.id.bt_edit_2);
        bt_edit_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CardEditActicity2.class);
                startActivity(intent);
            }
        });



    }
}
