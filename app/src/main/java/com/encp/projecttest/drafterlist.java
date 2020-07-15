package com.encp.projecttest;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.encp.projecttest.R;
import com.encp.projecttest.UserPage;
import com.encp.projecttest.adapter.drafterlistAdapter;
import com.encp.projecttest.item.listitem;

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

public class drafterlist extends AppCompatActivity {

    private RecyclerView upperrecyclerview;
    private drafterlistAdapter drafterlistadapter;
    private List<listitem> upperlist;

    String drafterlistuserName, drafterlistpjcGroup, drafterlistpjcPosition;
    int drafterdraftercount,drafterreviewcount,drafterpaymentcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2);

        upperrecyclerview = (RecyclerView) findViewById(R.id.upperrecyclerview);
        upperrecyclerview.setHasFixedSize(true);
        upperrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        upperlist = new ArrayList<listitem>();

        drafterlistadapter = new drafterlistAdapter(getApplicationContext(),upperlist);
        upperrecyclerview.setAdapter(drafterlistadapter);



        new BackgrounTasklist().execute();

    }

    class BackgrounTasklist extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://blrioun.cafe24.com/encp/php/drafterlist.php?pjcName=" + URLEncoder.encode(UserPage.perName, "UTF-8");
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
                    drafterlistpjcGroup = object.getString("pjcGroup");
                    drafterlistpjcPosition = object.getString("pjcPosition");
                    drafterlistuserName = object.getString("userName");
                    drafterdraftercount = object.getInt("draftercount");
                    drafterreviewcount = object.getInt("reviewcount");
                    drafterpaymentcount = object.getInt("paymentcount");


                    listitem listitem = new listitem(drafterlistpjcGroup, drafterlistpjcPosition, drafterlistuserName,
                            drafterdraftercount, drafterreviewcount, drafterpaymentcount);
                    upperlist.add(listitem);
                    drafterlistadapter.notifyDataSetChanged();
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
