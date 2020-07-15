package com.encp.projecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UserPage extends AppCompatActivity {

    static TextView upid, upposition, upgroup, upcode, upuname, uppjname,
    updrafter, upreview, uppayment, updistribute, upcirculation;

    public static String perRandom,perGroup,perPosition,perName, perBoolean,
            perdraftercount, perreviewcount, perpaymentcount, perrecipientcount, percirculationcount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        upid = (TextView) findViewById(R.id.upid);
        upposition = (TextView) findViewById(R.id.upposition);
        upgroup = (TextView) findViewById(R.id.upgroup);
        upcode = (TextView) findViewById(R.id.upcode);
        upuname = (TextView) findViewById(R.id.upuname);
        uppjname = (TextView) findViewById(R.id.uppjname);

        updrafter = (TextView) findViewById(R.id.upupper);
        upreview = (TextView) findViewById(R.id.upupper2);
        uppayment = (TextView) findViewById(R.id.upupper3);
        updistribute = (TextView) findViewById(R.id.updistribute);
        upcirculation = (TextView) findViewById(R.id.upcirculation);

        upid.setText(MainActivity.userID);
        upuname.setText(MainActivity.userName);
        upcode.setText(MainActivity.mpjcRandom);
        upposition.setText(MainActivity.mpjcPosition);
        upgroup.setText(MainActivity.mpjcGroup);
        uppjname.setText(MainActivity.mpjcName);

        if (MainActivity.userRanking.equals("관리자")){
            Button apply = (Button) findViewById(R.id.imgbuttonapply);
            apply.setVisibility(View.VISIBLE);
            Button list = (Button) findViewById(R.id.imgbuttonlist);
            list.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        new BackgroundTaskUserPage().execute();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void uponClick (View v)
    {
        Intent intent = null;
        switch (v.getId()){

                //상신
            case R.id.upupper:
                intent = new Intent(UserPage.this,Upper2.class);
                startActivityForResult(intent,1002);
                break;

                //검토
            case R.id.upupper2:
                intent = new Intent(UserPage.this, Upper3.class);
                startActivityForResult(intent,1003);
                break;

                //결제
            case R.id.upupper3:
                intent = new Intent(UserPage.this, Upper4.class);
                startActivityForResult(intent,1004);
                break;

                //배포
            case R.id.updistribute:
                intent = new Intent(UserPage.this,Distribute.class);
                startActivityForResult(intent,1005);
                break;

                //회람
            case R.id.upcirculation:
                intent = new Intent(UserPage.this, Circulation2.class);
                startActivityForResult(intent,1000);
                break;

                //결제 작성
            case R.id.uppaymentbtn:
                intent = new Intent(UserPage.this,Upper.class);
                startActivityForResult(intent,1000);
                break;

            //결제 현황
            case R.id.upstatus:
                intent = new Intent(UserPage.this,UpperStatus.class);
                startActivityForResult(intent,1001);
                break;

                //신청관리
            case R.id.imgbuttonapply:
                intent = new Intent(UserPage.this,Personnel.class);
                intent.putExtra("perRandom", perRandom);
                intent.putExtra("perName", perName);
                startActivityForResult(intent, 102);
                break;

                //인원관리
            case R.id.imgbuttonlist:
                intent = new Intent(UserPage.this,Personnel2.class);
                intent.putExtra("perRandom", perRandom);
                intent.putExtra("perName", perName);
                startActivityForResult(intent, 103);
                break;

        }
    }

    public static class BackgroundTaskUserPage extends AsyncTask<Void, Void, String>{

        String target;

        @Override
        protected void onPreExecute() {
            try{
                target = "http://blrioun.cafe24.com/encp/php/pjcmain.php?userID=" + URLEncoder.encode(MainActivity.userID,"UTF-8");
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
                    perName = object.getString("pjcName");
                    perGroup = object.getString("pjcGroup");
                    perPosition = object.getString("pjcPosition");
                    perRandom = object.getString("pjcRandom");
                    perBoolean = object.getString("pjcBoolean");
                    perdraftercount = object.getString("draftercount");
                    perreviewcount = object.getString("reviewcount");
                    perpaymentcount = object.getString("paymentcount");
                    perrecipientcount = object.getString("recipientcount");
                    percirculationcount = object.getString("circulationcount");
                    updrafter.setText(perdraftercount);
                    upreview.setText(perreviewcount);
                    uppayment.setText(perpaymentcount);
                    updistribute.setText(perrecipientcount);
                    upcirculation.setText(percirculationcount);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
