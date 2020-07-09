package com.encp.projecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.request.ProjectcreateRequest;

import org.json.JSONObject;

import java.util.Random;

public class Projectcreate extends AppCompatActivity {

    EditText pjcname, pjcgroup, pjcposition;
    Button pjcbutton;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectcreate);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        pjcname = (EditText) findViewById(R.id.pjcname);
        pjcgroup = (EditText) findViewById(R.id.pjcgroup);
        pjcposition = (EditText) findViewById(R.id.pjcposition);

        pjcbutton = (Button) findViewById(R.id.pjcbutton);

        //랜덤코드
        int a;
        int minimumValue = 1000000;
        int maximumValue = 9999999;
        Random random = new Random();
        a = random.nextInt(maximumValue - minimumValue) + minimumValue;
        final TextView pjccode = (TextView) findViewById(R.id.pjccode);
        pjccode.setText(String.valueOf(a));

        //프로젝트 생성
        pjcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;
                String userName = MainActivity.userName;
                String pjcName = pjcname.getText().toString();
                String pjcGroup = pjcgroup.getText().toString();
                String pjcPosition = pjcposition.getText().toString();
                String pjcRandom = pjccode.getText().toString();
                int pjcBoolean = 1;
                int draftercount = 0;
                int reviewcount = 0;
                int paymentcount = 0;
                int circulationcount = 0;

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(Projectcreate.this,"프로젝트 생성완료",Toast.LENGTH_SHORT).show();
//                                Intent intent1 = new Intent(Projectcreate.this,MainActivity.class);
//                                intent1.putExtra("userRanking", MainActivity.userRanking);
//                                intent1.putExtra("userID",MainActivity.userID);
                                setResult(100);
                                finish();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Projectcreate.this);
                                builder.setMessage("프로젝트 생성에 실패하였습니다.")
                                        .setTitle("프로젝트 생성")
                                        .setNegativeButton("취소",null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                ProjectcreateRequest projectcreateRequest = new ProjectcreateRequest(userID,userName,pjcName, pjcGroup, pjcPosition, pjcRandom, pjcBoolean,
                        draftercount, reviewcount, paymentcount, circulationcount, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Projectcreate.this);
                queue.add(projectcreateRequest);

            }
        });
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


}
