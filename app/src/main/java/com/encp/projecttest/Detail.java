package com.encp.projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.request.DetailRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.encp.projecttest.MainActivity.userID;

public class Detail extends AppCompatActivity {

    String dtname, dtposition, dtgroup, dtid;
    TextView detailid, detailname;
    EditText detailposition, detailgroup;
    Button detailsign;

    int detailboolean = 1;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        detailid = (TextView) findViewById(R.id.detailid);
        detailname = (TextView) findViewById(R.id.detailname);

        detailposition = (EditText) findViewById(R.id.detailposition);
        detailgroup = (EditText) findViewById(R.id.detailgroup);


        final Intent intent = getIntent();
        dtid = intent.getStringExtra("pid");
        dtname= intent.getStringExtra("pname");
        dtposition = intent.getStringExtra("pposition");
        dtgroup = intent.getStringExtra("pgroup");

        detailid.setText(dtid);
        detailname.setText(dtname);

        detailsign = (Button) findViewById(R.id.detailsign);
        detailsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pjcPosition = detailposition.getText().toString();
                String pjcGroup = detailgroup.getText().toString();
                String userID = detailid.getText().toString();
                int pjcBoolean = detailboolean;
                int draftercount = a;
                int reviewcount = a;
                int paymentcount = a;

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(Detail.this,"전송완료",Toast.LENGTH_SHORT).show();
                                setResult(102);
                                finish();
                            }
                        }catch (Exception e){

                        }
                    }
                };
                DetailRequest detailRequest = new DetailRequest(userID, pjcPosition,  pjcGroup, pjcBoolean, draftercount, reviewcount, paymentcount, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Detail.this);
                queue.add(detailRequest);

            }
        });


    }

    class BackgroundTaskDT extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute() {
            try{
//                target = "http://172.30.1.33/encp/Detail.php?userID"+ URLEncoder.encode(userID,"UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/Detail.php?userID"+ URLEncoder.encode(userID,"UTF-8");
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
                httpURLConnection.disconnect();
                inputStream.close();
                bufferedReader.close();
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
                String test;
                while (count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    test = object.getString("userAttendance");

                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
