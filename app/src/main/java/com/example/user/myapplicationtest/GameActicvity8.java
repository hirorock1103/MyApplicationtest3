package com.example.user.myapplicationtest;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActicvity8 extends AppCompatActivity {

    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_acticvity8);

        pager =findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        currentPage = 0;

    }
}
