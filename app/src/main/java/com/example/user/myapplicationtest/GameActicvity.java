package com.example.user.myapplicationtest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActicvity extends AppCompatActivity implements Fragment1.Fragment1Listner {

    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private int currentPage;
    private int total;
    private TextView textView;
    private ImageView success_image;
    private int langSetting;//100 japanese 200 english

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_acticvity);
        textView = findViewById(R.id.textView);
        success_image = findViewById(R.id.success_image);
        success_image.bringToFront();
        success_image.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        langSetting = sharedPreferences.getInt("LANG", 0);

        pager =findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, langSetting);
        pager.setAdapter(adapter);
        total = adapter.getCount();
        currentPage = 1;
        setNavigation(currentPage, total);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(state == ViewPager.SCROLL_STATE_SETTLING){
                    success_image.setVisibility(View.INVISIBLE);
                    currentPage = pager.getCurrentItem() + 1;
                    setNavigation(currentPage, total);
                }
            }
        });

        currentPage = 0;

    }

    public void setNavigation(int currentPage, int total){

        textView.setText(currentPage + "/" + total);
    }

    @Override
    public void getfeedback(int result , ArrayList<String> candidates ) {
        if(result == 1){

            Animation myAnim = AnimationUtils.loadAnimation(GameActicvity.this, R.anim.bounce);
            success_image.setAnimation(myAnim);
            success_image.setVisibility(View.VISIBLE);
            success_image.setAlpha(180);

        }else{

            success_image.setVisibility(View.INVISIBLE);
            String str = "";
            for (String s : candidates){
                str = str + s + ",";
            }
            str = str.substring(0, str.length()-1);
            Toast.makeText(GameActicvity.this, "ヒットしませんでした！\n[候補]\n" + str, Toast.LENGTH_LONG).show();

        }
    }
}
