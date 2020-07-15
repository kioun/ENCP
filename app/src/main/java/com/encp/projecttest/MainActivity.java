package com.encp.projecttest;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    ImageButton imgbtnlist,imgbtnapply;


    public static String userID,userRanking,userName;

    public static String mpjcName, mpjcGroup, mpjcPosition, mpjcRandom,mpjcBoolean,
            mdraftercount, mreviewcount, mpaymentcount, mrecipientcount, mcirculationcount;
    TextView mainid,mainname, imgbtnconnect, imgbtncode, imgbtncreate;

    private long finishtime = 2000;
    private long time = 0;

    SharedPreferences setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userRanking = intent.getStringExtra("userRanking");
        userName = intent.getStringExtra("userName");
        Toast.makeText(this,userID+"님 환영합니다.",Toast.LENGTH_LONG).show();

        mainid = (TextView) findViewById(R.id.mainid);
        mainid.setText(userID);
        mainname = (TextView) findViewById(R.id.mainname);
        mainname.setText(userName);


        SharedPreferences.Editor editor;
        setting = getSharedPreferences("setting",0);
        editor = setting.edit();

        editor.putString("ID",userID);
        editor.commit();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        onResume();

        //프로젝트 생성
            imgbtncreate = (TextView) findViewById(R.id.imgbuttoncreate);
            imgbtncreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mpjcRandom == null) {
                        Intent intent = new Intent(MainActivity.this, Projectcreate.class);
                        startActivityForResult(intent, 100);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("프로젝트가 존재합니다.")
                                .setNegativeButton("확인",null)
                                .create()
                                .show();
                    }
                }
            });

        //프로젝트 코드
        imgbtncode = (TextView) findViewById(R.id.imgbuttoncode);
        imgbtncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mpjcRandom == null) {
                    Intent intent3 = new Intent(MainActivity.this, Projectcode.class);
                    intent3.putExtra("perRandom", mpjcRandom);
                    startActivityForResult(intent3, 101);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setNegativeButton("확인",null)
                            .setMessage("프로젝트가 존재합니다.")
                            .create()
                            .show();
                }
            }
        });
            //프로젝트 접속
            imgbtnconnect = (TextView) findViewById(R.id.imgbuttonconnect);
            imgbtnconnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mpjcRandom != null ) {
                        Intent intent4 = new Intent(MainActivity.this, UserPage.class);
                        startActivity(intent4);
                    } else {
                        AlertDialog.Builder albuilder = new AlertDialog.Builder(MainActivity.this);
                        albuilder.setMessage("프로젝트 생성 또는 가입된 프로젝트가 없습니다.")
                                .setNegativeButton("확인", null)
                                .create()
                                .show();
                    }
                }
            });

    }

    @Override
    protected void onResume() {
        super.onResume();
        new BackgroundTaskMain().execute();
    }


    //뒤로가기로 종료
    @Override
    public void onBackPressed() {
        long temptime = System.currentTimeMillis();
        long intavaltime = temptime - time;

        if (0 <= intavaltime && finishtime >= intavaltime)
        {
            super.onBackPressed();
        }
        else {
            time = temptime;
            Toast.makeText(MainActivity.this,"'뒤로'버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_LONG).show();
        }
    }

    public static class BackgroundTaskMain extends AsyncTask<Void, Void, String>{

        String target;

        @Override
        protected void onPreExecute() {
            try{
                target = "http://blrioun.cafe24.com/encp/php/pjcmain.php?userID=" + URLEncoder.encode(userID,"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count=0;

                while (count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    mpjcName = object.getString("pjcName");
                    mpjcGroup = object.getString("pjcGroup");
                    mpjcPosition = object.getString("pjcPosition");
                    mpjcRandom = object.getString("pjcRandom");
                    mpjcBoolean = object.getString("pjcBoolean");
                    mdraftercount = object.getString("draftercount");
                    mreviewcount = object.getString("reviewcount");
                    mpaymentcount = object.getString("paymentcount");
                    mrecipientcount = object.getString("recipientcount");
                    mcirculationcount = object.getString("circulationcount");
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
