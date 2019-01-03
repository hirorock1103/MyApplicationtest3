package com.example.user.myapplicationtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardEditActicity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    CardContentsManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit_acticity2);

        recyclerView = findViewById(R.id.recycler_view);

        manager = new CardContentsManager(this);

        List<CardContents> list = manager.getCardList();

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);


    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView image_src;
        Button btn_delete;
        ConstraintLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            image_src = itemView.findViewById(R.id.image_src);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            layout = itemView.findViewById(R.id.layout);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        List<CardContents> list;

        MyAdapter(List<CardContents> list){
            this.list = list;
        }

        public void setList(List<CardContents> list){
            this.list = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(CardEditActicity2.this).inflate(R.layout.list_card_item, parent, false);

            MyViewHolder holder  = new MyViewHolder(view);

            return holder;

    }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            CardContents card = list.get(position);

            holder.title.setText(card.getCard_title());
            if(card.getCard_image() != null){
                Bitmap image = BitmapFactory.decodeByteArray(card.getCard_image(),0,card.getCard_image().length);
                holder.image_src.setImageBitmap(image);
            }

            final int cardId = card.getCard_id();
            final int pos = position;
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    manager.delete(cardId);

                    //adapter.notifyItemChanged(pos);
                    list = manager.getCardList();
                    adapter.setList(list);
                    recyclerView.setAdapter(adapter);

                    //recyclerView.setAdapter(adapter);

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }




}
