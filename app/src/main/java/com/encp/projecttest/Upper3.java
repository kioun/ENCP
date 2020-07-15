package com.encp.projecttest;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.encp.projecttest.adapter.Upper3Adapter;
import com.encp.projecttest.item.UpperItem;

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

public class Upper3 extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private Upper3Adapter upperadapter;
    private List<UpperItem> upperlist;

    String upper3userName, upper3title, upper3drafter,
            upper3pjcName, upper3pjcGroup, upper3review, upper3payment, upper3recipient, upper3contents, upper3userID;

    int upper3count, upper3number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<UpperItem>();

        upperadapter = new Upper3Adapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(upperadapter);

        new BackgrounTaskUpper3().execute();
    }

    class BackgrounTaskUpper3 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
//                target = "http://172.30.1.33/encp/Upper3.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
//                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
//                        + "&review=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/Upper3.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
                        + "&review=" + URLEncoder.encode(MainActivity.userName, "UTF-8")
                        + "&reviewgroup=" + URLEncoder.encode(UserPage.perGroup,"UTF-8")
                        + "&reviewposition=" + URLEncoder.encode(UserPage.perPosition,"UTF-8");
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
                    upper3userName = object.getString("nbuserName");
                    upper3title = object.getString("title");
                    upper3drafter = object.getString("drafter");
                    upper3pjcName = object.getString("pjcName");
                    upper3pjcGroup = object.getString("pjcGroup");
                    upper3review = object.getString("review");
                    upper3payment = object.getString("payment");
                    upper3recipient = object.getString("recipient");
                    upper3contents = object.getString("contents");
                    upper3userID = object.getString("userID");
                    upper3count = object.getInt("distributecount");
                    upper3number = object.getInt("noticenumber");
                    UpperItem upperItem = new UpperItem(upper3userID, upper3pjcName, upper3pjcGroup, upper3drafter, upper3review, upper3payment, upper3recipient, upper3contents,
                            upper3userName, upper3title, upper3count, upper3number);
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
