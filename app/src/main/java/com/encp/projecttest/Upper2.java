package com.encp.projecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

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

public class Upper2 extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private Upper2Adapter upperadapter;
    private List<UpperItem> upperlist;

    String upper2userName, upper2title, upper2drafter,
            upper2pjcName, upper2pjcGroup, upper2review, upper2payment, upper2recipient, upper2contents, upper2userID;

    int upper2count, upper2number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<UpperItem>();

        upperadapter = new Upper2Adapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(upperadapter);

        new BackgrounTaskUpper2().execute();

    }

    class BackgrounTaskUpper2 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
//                target = "http://172.30.1.33/encp/Upper.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
////                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
////                        + "&drafter=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/Upper.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
                        + "&drafter=" + URLEncoder.encode(MainActivity.userName, "UTF-8")
                        + "&draftergroup=" + URLEncoder.encode(UserPage.perGroup,"UTF-8")
                        + "&drafterposition=" + URLEncoder.encode(UserPage.perPosition,"UTF-8");
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
                    upper2userName = object.getString("nbuserName");
                    upper2title = object.getString("title");
                    upper2drafter = object.getString("drafter");
                    upper2pjcName = object.getString("pjcName");
                    upper2pjcGroup = object.getString("pjcGroup");
                    upper2review = object.getString("review");
                    upper2payment = object.getString("payment");
                    upper2recipient = object.getString("recipient");
                    upper2contents = object.getString("contents");
                    upper2userID = object.getString("userID");
                    upper2count = object.getInt("distributecount");
                    upper2number = object.getInt("noticenumber");

                    UpperItem upperItem = new UpperItem(upper2userID, upper2pjcName, upper2pjcGroup, upper2drafter, upper2review, upper2payment, upper2recipient, upper2contents,
                            upper2userName, upper2title, upper2count, upper2number);
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
