package com.example.user.myapplicationtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class EditListViewPagerAdapter extends FragmentPagerAdapter {

    private String[] LANG;

    public EditListViewPagerAdapter(FragmentManager fm, Context context, int lang) {
        super(fm);
        CardContentsManager manager = new CardContentsManager(context);
        if(lang == 200){
            LANG = manager.getHiranagqaDataExistsOnly(Common.ALPHABET);
        }else{
            LANG = manager.getHiranagqaDataExistsOnly(Common.HIRANAGQA);
        }

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        Bundle bundle;
        fragment = new Fragment1();
        bundle = new Bundle();
        bundle.putString("key",LANG[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return LANG.length;
    }


}
