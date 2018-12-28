package com.example.user.myapplicationtest;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CardEditActivity extends AppCompatActivity {

//    private String[] languages = {"J","E"};
//    private ViewPager pager;
//    private TabLayout tabLayout;
//    private FragmentPagerAdapter adapter;

    private List<CardContents> list;
    private CardContentsManager manager;
    private MyAdapter adapter;

    ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

//        pager =findViewById(R.id.view_pager);
//        tabLayout =findViewById(R.id.tabLayout);
//        adapter = new EditListViewPagerAdapter(getSupportFragmentManager(), this, 100);
//        pager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(pager);

        //List表示してLongClickで削除する

        list_view = findViewById(R.id.list_view);

        manager = new CardContentsManager(this);
        list = manager.getCardList();

        for(CardContents c : list){
            Log.i("INFO", c.getCard_title());
        }
        adapter = new MyAdapter(this, list);
        list_view.setAdapter(adapter);


    }

    public void reload(){
        list = manager.getCardList();
        adapter.setList(list);
        adapter.notifyDataSetChanged();

    }

    public class MyAdapter extends ArrayAdapter<CardContents>{

        private List<CardContents> list;

        public MyAdapter(@NonNull Context context, List<CardContents> list) {
            super(context, R.layout.list_card_view, list);
            this.list = list;
        }

        public void setList(List<CardContents> list){
            this.list = list;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.list_card_view, parent, false);

            try{
                TextView title = view.findViewById(R.id.title);
                title.setText(list.get(position).getCard_title());
                Button delete = view.findViewById(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        manager.delete(list.get(position).getCard_id());
                        reload();
                    }
                });
            }catch(Exception e){
                Toast.makeText(CardEditActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }



            return view;

        }
    }

}
