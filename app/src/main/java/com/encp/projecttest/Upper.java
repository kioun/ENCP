package com.encp.projecttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.list.drafterlist;
import com.encp.projecttest.request.UpperRequest;

import org.json.JSONObject;

public class Upper extends AppCompatActivity {

    TextView upperpjname, uppername, upperpjgroup;
    TextView upperdrafter, upperreview, upperpayment, upperrecipient;
    EditText uppertitle, uppercontents;
    Button upperbtn,listbtn;

    String draftergroup,drafterposition,reviewgroup,reviewposition,paymentgroup,paymentposition,recipientgroup,recipientposition;
//    String[] items = {"aa", "bb", "cc", "dd"};
//    String[] userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper);

        upperpjname = (TextView) findViewById(R.id.upperpjname);
        uppername = (TextView) findViewById(R.id.uppername);
        upperpjgroup = (TextView) findViewById(R.id.upperpjgroup);

        upperdrafter = (TextView) findViewById(R.id.upperdrafter);
        upperreview = (TextView) findViewById(R.id.upperreview);
        upperpayment = (TextView) findViewById(R.id.upperpayment);
        upperrecipient = (TextView) findViewById(R.id.upperrecipient);
        uppertitle = (EditText) findViewById(R.id.uppertitle);
        uppercontents = (EditText) findViewById(R.id.uppercontents);

        upperbtn = (Button) findViewById(R.id.upperbtn);
        listbtn = (Button) findViewById(R.id.listbtn);

        //유저목록리스트
//        new BackgroundTaskuserlist().execute();

//        //보내는사람 목록
//        listbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Upper.this);
//                builder.setTitle("목록")
//                        .setItems(userlist, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(getApplicationContext(),userlist[i],Toast.LENGTH_LONG).show();
//                            }
//                        });
//                 AlertDialog alertDialog = builder.create();
//                 alertDialog.show();
//            }
//        });

//        Intent intent = getIntent();

        upperpjname.setText(UserPage.perName);
        upperpjgroup.setText(UserPage.perGroup);
        uppername.setText(MainActivity.userName);
        final int a = Integer.parseInt(UserPage.perdraftercount);
        final int b = Integer.parseInt(UserPage.perreviewcount);
        final int c = Integer.parseInt(UserPage.perpaymentcount);

        upperbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;
                String pjcName = upperpjname.getText().toString();
                String pjcGroup = upperpjgroup.getText().toString();
                String nbuserName = uppername.getText().toString();
                String drafter = upperdrafter.getText().toString();
                String review = upperreview.getText().toString();
                String payment = upperpayment.getText().toString();
                String recipient = upperrecipient.getText().toString();
                String title = uppertitle.getText().toString();
                String contents = uppercontents.getText().toString();
                int distributecount = 0;
                int draftercount = (a+1);
                int reviewcount = (b+1);
                int paymentcount = (c+1);

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(Upper.this,"작성완료",Toast.LENGTH_SHORT).show();
                                setResult(1000);
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                UpperRequest upperRequest = new UpperRequest(userID, pjcName, pjcGroup, nbuserName,
                        drafter, draftergroup, drafterposition,
                        review, reviewgroup, reviewposition,
                        payment, paymentgroup, paymentposition,
                        recipient, recipientgroup, recipientposition,
                        title, contents, distributecount,
                        draftercount, reviewcount, paymentcount, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Upper.this);
                queue.add(upperRequest);
            }
        });
    }

    //유저 리스트
    public void upperlist (View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.upperdrafter:
                intent = new Intent(Upper.this, drafterlist.class);
                startActivityForResult(intent,111);
                break;
            case R.id.upperreview:
                intent = new Intent(Upper.this,drafterlist.class);
                startActivityForResult(intent,222);
                break;
            case R.id.upperpayment:
                intent = new Intent(Upper.this,drafterlist.class);
                startActivityForResult(intent,333);
                break;
            case R.id.upperrecipient:
                intent = new Intent(Upper.this,drafterlist.class);
                startActivityForResult(intent,444);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK){
            Toast.makeText(Upper.this,"실패",Toast.LENGTH_LONG).show();
        }
        if (requestCode == 111){
            String drafter1 = data.getStringExtra("drafter");
            upperdrafter.setText(drafter1);
            draftergroup = data.getStringExtra("draftergroup");
            drafterposition = data.getStringExtra("drafterposition");
        }
        if (requestCode == 222){
            String review1 = data.getStringExtra("review");
            upperreview.setText(review1);
            reviewgroup = data.getStringExtra("reviewgroup");
            reviewposition = data.getStringExtra("reviewposition");
        }
        if (requestCode == 333){
            String payment1 = data.getStringExtra("payment");
            upperpayment.setText(payment1);
            paymentgroup = data.getStringExtra("paymentgroup");
            paymentposition = data.getStringExtra("paymentposition");
        }
        if (requestCode == 444){
            String recipient1 = data.getStringExtra("recipient");
            upperrecipient.setText(recipient1);
            recipientgroup = data.getStringExtra("recipientgroup");
            recipientposition = data.getStringExtra("recipientposition");
        }
    }

    //    class BackgroundTaskuserlist extends AsyncTask<Void, Void, String>{
//
//        String target;
//
//        @Override
//        protected void onPreExecute() {
//            try {
//                //                target = "http://172.30.1.33/encp/Mainnotice.php";
//                target = "http://blrioun.cafe24.com/encp/php/userlist.php?pjcName="+ URLEncoder.encode(UserPage.perName,"UTF-8")
//                + "&pjcRandom=" + URLEncoder.encode(UserPage.perRandom,"UTF-8");
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try{
//                URL url = new URL(target);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String temp;
//                StringBuilder stringBuilder = new StringBuilder();
//                while ((temp = bufferedReader.readLine()) != null){
//                    stringBuilder.append(temp+"\n");
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return stringBuilder.toString().trim();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try{
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray jsonArray = jsonObject.getJSONArray("response");
//                int count=0;
//
//                while (count < jsonArray.length()){
//                    JSONObject object = jsonArray.getJSONObject(count);
//                    test = object.getString("userName");
//
//                    Toast.makeText(getApplicationContext(),test,Toast.LENGTH_LONG).show();
//                    userlist[count] = test;
//                    count++;
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

}
