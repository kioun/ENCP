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

public class Upper4 extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private Upper4Adapter upperadapter;
    private List<UpperItem> upperlist;

    String upper4userName, upper4title, upper4drafter,
            upper4pjcName, upper4pjcGroup, upper4review, upper4payment, upper4recipient, upper4contents, upper4userID;

    int upper4count, upper4number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<UpperItem>();

        upperadapter = new Upper4Adapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(upperadapter);

        new BackgrounTaskUpper4().execute();
    }

    class BackgrounTaskUpper4 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
//                target = "http://172.30.1.33/encp/Upper4.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
//                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
//                        + "&payment=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/Upper4.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
                        + "&payment=" + URLEncoder.encode(MainActivity.userName, "UTF-8")
                        + "&paymentgroup=" + URLEncoder.encode(UserPage.perGroup,"UTF-8")
                        + "&paymentposition=" + URLEncoder.encode(UserPage.perPosition,"UTF-8");
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
                    upper4userName = object.getString("nbuserName");
                    upper4title = object.getString("title");
                    upper4drafter = object.getString("drafter");
                    upper4pjcName = object.getString("pjcName");
                    upper4pjcGroup = object.getString("pjcGroup");
                    upper4review = object.getString("review");
                    upper4payment = object.getString("payment");
                    upper4recipient = object.getString("recipient");
                    upper4contents = object.getString("contents");
                    upper4userID = object.getString("userID");
                    upper4count = object.getInt("distributecount");
                    upper4number = object.getInt("noticenumber");
                    UpperItem upperItem = new UpperItem(upper4userID, upper4pjcName, upper4pjcGroup, upper4drafter, upper4review, upper4payment, upper4recipient, upper4contents,
                            upper4userName, upper4title, upper4count, upper4number);
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
