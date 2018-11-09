package com.example.user.myapplicationtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] HIRAGANA;

    public ViewPagerAdapter(FragmentManager fm, Context context, int lang) {
        super(fm);
        CardContentsManager manager = new CardContentsManager(context);
        if(lang == 200){
            HIRAGANA = manager.getHiranagqaDataExistsOnly(Common.ALPHABET);
        }else{
            HIRAGANA = manager.getHiranagqaDataExistsOnly(Common.HIRANAGQA);
        }

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