package com.example.user.myapplicationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String[] HIRAGANA = {"あ","い","う","え","お","か","き","く","け","こ","さ","し","す","せ","そ"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        Bundle bundle;
        fragment = new Fragment1();
        bundle = new Bundle();
        bundle.putString("key",HIRAGANA[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return HIRAGANA.length;
    }
}