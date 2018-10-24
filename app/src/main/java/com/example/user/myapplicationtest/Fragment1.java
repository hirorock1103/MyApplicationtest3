package com.example.user.myapplicationtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends android.support.v4.app.Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //
        Bundle bundle = getArguments();
        String val = bundle.get("key").toString();

        View view = inflater.inflate(R.layout.fragment1, container, false);
        textView = view.findViewById(R.id.f_title);
        textView.setText(val);
        return view;

    }
}
