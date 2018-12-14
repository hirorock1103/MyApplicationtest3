package com.example.user.myapplicationtest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CardEditActivity extends AppCompatActivity {

    private String[] languages = {"J","E"};
    private ViewPager pager;
    private TabLayout tabLayout;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        pager =findViewById(R.id.view_pager);
        tabLayout =findViewById(R.id.tabLayout);
        adapter = new EditListViewPagerAdapter(getSupportFragmentManager(), this, 100);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }
}
