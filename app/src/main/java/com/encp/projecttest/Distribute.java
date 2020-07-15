package com.encp.projecttest;

import android.os.AsyncTask;
import android.os.Bundle;

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

public class Distribute extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private DistributeAdapter upperadapter;
    private List<UpperItem> upperlist;

    String upper5userName, upper5title, upper5drafter,
            upper5pjcName, upper5pjcGroup, upper5review, upper5payment, upper5recipient, upper5contents, upper5userID;

    int upper5count, upper5number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<UpperItem>();

        upperadapter = new DistributeAdapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(upperadapter);

        new BackgrounTaskUpper5().execute();
    }

    class BackgrounTaskUpper5 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
//                target = "http://172.30.1.33/encp/Upper3.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
//                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
//                        + "&review=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/Distribute.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
                        + "&nbuserName=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                httpURLConnection.disconnect();
                inputStream.close();
                bufferedReader.close();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
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
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    upper5userName = object.getString("nbuserName");
                    upper5title = object.getString("title");
                    upper5drafter = object.getString("drafter");
                    upper5pjcName = object.getString("pjcName");
                    upper5pjcGroup = object.getString("pjcGroup");
                    upper5review = object.getString("review");
                    upper5payment = object.getString("payment");
                    upper5recipient = object.getString("recipient");
                    upper5contents = object.getString("contents");
                    upper5userID = object.getString("userID");
                    upper5count = object.getInt("distributecount");
                    upper5number = object.getInt("noticenumber");
                    UpperItem upperItem = new UpperItem(upper5userID, upper5pjcName, upper5pjcGroup, upper5drafter, upper5review, upper5payment, upper5recipient, upper5contents,
                            upper5userName, upper5title, upper5count, upper5number);
                    upperlist.add(upperItem);
                    upperadapter.notifyDataSetChanged();
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
