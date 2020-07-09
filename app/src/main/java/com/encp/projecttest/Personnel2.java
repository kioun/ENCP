package com.encp.projecttest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Personnel2 extends AppCompatActivity {

    String personnel2ID, personnel2Name, personnel2Group,personnel2Position;

    TextView psname,pscode;

    String userid,perRandom,perName;

    private RecyclerView person2recyclerview;
    private Personnel2Adapter person2adapter;
    private List<Personnel2Item> person2list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //recyclerview 인원 리스트
        person2recyclerview = (RecyclerView) findViewById(R.id.personrecycler);
        person2recyclerview.setHasFixedSize(true);
        person2recyclerview.setLayoutManager(new LinearLayoutManager(this));
        person2list = new ArrayList<Personnel2Item>();

        person2adapter = new Personnel2Adapter(getApplicationContext(),person2list);
        person2recyclerview.setAdapter(person2adapter);

        psname = (TextView) findViewById(R.id.pjcname);
        pscode = (TextView) findViewById(R.id.pjccode);

        userid = MainActivity.userID;
        Intent intent = getIntent();
        perRandom = intent.getStringExtra("perRandom");
        perName = intent.getStringExtra("perName");
        pscode.setText(perRandom);
        psname.setText(perName);

        new BackgroundTaskPerson2().execute();

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

    class BackgroundTaskPerson2 extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try{
//                target = "http://172.30.1.33/encp/pjclist2.php?pjcRandom=" + URLEncoder.encode(perRandom,"UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/pjclist2.php?pjcRandom=" + URLEncoder.encode(perRandom,"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            //target = "http://172.30.1.19/pjclist.php";
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
                    stringBuilder.append(temp + "\n");
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
                int count = 0;

                while (count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    personnel2ID = object.getString("userID");
                    personnel2Name = object.getString("userName");
                    personnel2Group = object.getString("pjcGroup");
                    personnel2Position = object.getString("pjcPosition");

                    Personnel2Item personnel2Item = new Personnel2Item( personnel2ID, personnel2Name, personnel2Group, personnel2Position);
                    person2list.add(personnel2Item);
                    person2adapter.notifyDataSetChanged();
                    count++;

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
