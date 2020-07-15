package com.encp.projecttest;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.encp.projecttest.adapter.Circulation2Adapter;
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

public class Circulation2 extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private Circulation2Adapter cladapter;
    private List<UpperItem> upperlist;

    String cluserName, cltitle, cldrafter,
            clpjcName, clpjcGroup, clreview, clpayment, clrecipient, clcontents, cluserID;

    int clcount, clnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<UpperItem>();

        cladapter = new Circulation2Adapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(cladapter);

        new BackgrounTaskCirculation2().execute();
    }

    class BackgrounTaskCirculation2 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
//                target = "http://172.30.1.33/encp/Upper3.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
//                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
//                        + "&review=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/Circulation2.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
                        + "&recipient=" + URLEncoder.encode(MainActivity.userName, "UTF-8")
                        + "&recipientgroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
                        + "&recipientposition=" + URLEncoder.encode(UserPage.perPosition, "UTF-8");
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
                    cluserName = object.getString("nbuserName");
                    cltitle = object.getString("title");
                    cldrafter = object.getString("drafter");
                    clpjcName = object.getString("pjcName");
                    clpjcGroup = object.getString("pjcGroup");
                    clreview = object.getString("review");
                    clpayment = object.getString("payment");
                    clrecipient = object.getString("recipient");
                    clcontents = object.getString("contents");
                    cluserID = object.getString("userID");
                    clcount = object.getInt("distributecount");
                    clnumber = object.getInt("noticenumber");
                    UpperItem upperItem = new UpperItem(cluserID, clpjcName, clpjcGroup, cldrafter, clreview, clpayment, clrecipient, clcontents,
                            cluserName, cltitle, clcount, clnumber);
                    upperlist.add(upperItem);
                    cladapter.notifyDataSetChanged();
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
