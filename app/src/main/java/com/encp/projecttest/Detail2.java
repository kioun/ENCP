package com.encp.projecttest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.request.DetailRequest;

import org.json.JSONObject;

public class Detail2 extends AppCompatActivity {

    String dtname, dtposition, dtgroup, dtid;
    TextView detailid, detailname;
    EditText detailposition, detailgroup;
    Button detailsign, detailbtn, detaildelete;

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
        detailbtn = (Button) findViewById(R.id.detailbtn);
        detaildelete = (Button) findViewById(R.id.detaildelete);

        detailbtn.setVisibility(View.VISIBLE);
        detaildelete.setVisibility(View.VISIBLE);
        detailsign.setVisibility(View.GONE);
        detailbtn.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(Detail2.this,"회원정보 변경완료",Toast.LENGTH_SHORT).show();
                                setResult(103);
                                finish();
                            }
                        }catch (Exception e){

                        }
                    }
                };
                DetailRequest detailRequest = new DetailRequest(userID, pjcPosition,  pjcGroup, pjcBoolean, draftercount, reviewcount, paymentcount, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Detail2.this);
                queue.add(detailRequest);

            }
        });

        detaildelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Detail2.this);
                builder.setTitle("프로젝트 추방")
                        .setMessage("정말 프로젝트에서 추방시키겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String userID = detailid.getText().toString();

                                Response.Listener<String> responselistener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            boolean success = jsonObject.getBoolean("success");
                                            if (success){
                                                Toast.makeText(Detail2.this,"프로젝트 추방완료",Toast.LENGTH_LONG).show();
                                                finish();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                DetailDelete detailDelete = new DetailDelete(userID, responselistener);
                                RequestQueue queue = Volley.newRequestQueue(Detail2.this);
                                queue.add(detailDelete);
                                }
                        })
                        .setNegativeButton("취소",null)
                        .show()
                        .create();
            }
        });

    }
}
