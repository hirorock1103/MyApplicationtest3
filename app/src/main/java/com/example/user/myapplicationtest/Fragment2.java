package com.example.user.myapplicationtest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Fragment2 extends Fragment implements View.OnTouchListener,TextWatcher {

    private int IMAGE_GALLERY_REQUEST = 100;
    private InputMethodManager inputMethodManager;
    private ConstraintLayout mainlayout;
    private byte[] iconByteImage;
    private TextView title;
    private EditText editTitle;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reg1, container, false);
        editTitle = view.findViewById(R.id.edit_f_title_all);
        title = view.findViewById(R.id.f_title);
        imageView = view.findViewById(R.id.image);
        mainlayout = view.findViewById(R.id.mainlayout);
        Button regBtn = view.findViewById(R.id.register);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validation
                CardContentsManager manager = new CardContentsManager(getActivity());

                //title , image
                int errorCount = 0;
                if(editTitle.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"タイトルを入力してください。", Toast.LENGTH_SHORT).show();
                    errorCount++;
                }else{

                    if(manager.isExistsSameTitle(editTitle.getText().toString()) == true){
                        Toast.makeText(getActivity(),"同一タイトルが既に存在します。", Toast.LENGTH_SHORT).show();
                        errorCount++;
                    }

                }
                if(iconByteImage == null){
                    Toast.makeText(getActivity(),"画像を選択してください。", Toast.LENGTH_SHORT).show();
                    errorCount++;
                }

                if(errorCount == 0){
                    //insert data
                    CardContents card = new CardContents();
                    card.setCard_title(editTitle.getText().toString());
                    card.setCard_image(iconByteImage);

                    int id = manager.addCardContents(card);

                    if(id > 0){
                        Toast.makeText(getActivity(), "登録しました！", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "登録に失敗しました。", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        Button getImageBtn = view.findViewById(R.id.getImageButton);
        getImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ファイルから画像取得する
                Intent photoPickIntent = new Intent(Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();

                Uri data = Uri.parse(pictureDirectoryPath);

                photoPickIntent.setDataAndType(data, "image/*");

                startActivityForResult(photoPickIntent,IMAGE_GALLERY_REQUEST );

            }
        });
        view.setOnTouchListener(this);
        editTitle.addTextChangedListener(this);
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == getActivity().RESULT_OK){

            if(requestCode == IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();

                InputStream inputStream;
                BitmapFactory.Options imageOptions;

                try {
                    inputStream = getActivity().getContentResolver().openInputStream(imageUri);

                    //画像サイズ情報
                    imageOptions = new BitmapFactory.Options();
                    imageOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStream,null,imageOptions);
                    inputStream.close();

                    //再度読み込み
                    inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                    int width = imageOptions.outWidth;

                    int p = 1;
                    while(width > 400){
                        //縮小率を決める
                        p *= 2;
                        width /= p;
                    }

                    Bitmap imageBitmap;
                    if(p > 1){
                        //縮小
                        imageOptions = new BitmapFactory.Options();
                        imageOptions.inSampleSize = p;
                        imageBitmap = BitmapFactory.decodeStream(inputStream, null,imageOptions);

                    }else{
                        //等倍
                        imageBitmap = BitmapFactory.decodeStream(inputStream, null,null);
                    }

                    inputStream.close();


                    //bitmapをblob保存用に変換
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    iconByteImage = stream.toByteArray();
                    stream.close();

                    //確認用の画像
                    Bitmap img = BitmapFactory.decodeByteArray(iconByteImage, 0, iconByteImage.length);
                    imageView.setImageBitmap(img);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainlayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        mainlayout.requestFocus();

        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable editable) {

        if(editable.toString().isEmpty() == false){
            String tmp = editable.toString();
            title.setText(String.valueOf(tmp.charAt(0)));
        }else{
            title.setText("");
        }

    }
}
