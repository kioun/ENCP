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

public class UpperStatus extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private UpperStatusAdapter upperadapter;
    private List<UpperItem> upperlist;

    String upperstatususerName, upperstatustitle, upperstatusdrafter,
            upperstatuspjcName, upperstatuspjcGroup, upperstatusreview, upperstatuspayment, upperstatusrecipient, upperstatuscontents, upperstatususerID;

    int upperstatuscount, upperstatusnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<UpperItem>();

        upperadapter = new UpperStatusAdapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(upperadapter);

        new BackgrounTaskUpperStatus().execute();

    }

    class BackgrounTaskUpperStatus extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
//                target = "http://172.30.1.33/encp/Upper.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
////                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
////                        + "&drafter=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
                target = "http://blrioun.cafe24.com/encp/php/UpperStatus.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8")
                        + "&pjcGroup=" + URLEncoder.encode(UserPage.perGroup, "UTF-8")
                        + "&userID=" + URLEncoder.encode(MainActivity.userName, "UTF-8");
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
                    upperstatususerName = object.getString("nbuserName");
                    upperstatustitle = object.getString("title");
                    upperstatusdrafter = object.getString("drafter");
                    upperstatuspjcName = object.getString("pjcName");
                    upperstatuspjcGroup = object.getString("pjcGroup");
                    upperstatusreview = object.getString("review");
                    upperstatuspayment = object.getString("payment");
                    upperstatusrecipient = object.getString("recipient");
                    upperstatuscontents = object.getString("contents");
                    upperstatususerID = object.getString("userID");
                    upperstatuscount = object.getInt("distributecount");
                    upperstatusnumber = object.getInt("noticenumber");

                    UpperItem upperItem = new UpperItem(upperstatususerID, upperstatuspjcName, upperstatuspjcGroup, upperstatusdrafter, upperstatusreview, upperstatuspayment, upperstatusrecipient, upperstatuscontents,
                            upperstatususerName, upperstatustitle, upperstatuscount, upperstatusnumber);
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
