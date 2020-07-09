package com.encp.projecttest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Loding extends AppCompatActivity {

    final private int REQUEST_WHIDH = 512;
    final private int REQUEST_HEIGHT = 512;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        imageView = (ImageView) findViewById(R.id.titleimg);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.drawable.titleimage2,options);
        int request_Width;
        options.inSampleSize = setSimpleSize(options,REQUEST_WHIDH,REQUEST_HEIGHT);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.encp_mainimg,options);
        imageView.setImageBitmap(bitmap);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Loding.this, Login.class);

                startActivity(intent);
                finish();
            }
        },4000);//4초
    }

    private int setSimpleSize(BitmapFactory.Options options, int requestWidth, int requestHeight){

        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        int size = 1;

        while (requestWidth < originalWidth || requestHeight < originalHeight){
            originalWidth = originalWidth / 2;
            originalHeight = originalHeight / 2;

            size = size * 2;
        }
        return size;
    }

}




