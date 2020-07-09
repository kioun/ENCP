package com.encp.projecttest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.request.LoginRequest;

import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView signbutton,loginbutton;
    EditText loginid,loginpwd;
    CheckBox logincheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signbutton = (TextView) findViewById(R.id.signbutton);
        loginbutton = (TextView) findViewById(R.id.loginbutton);
        loginid = (EditText) findViewById(R.id.loginid);
        loginpwd = (EditText) findViewById(R.id.loginpwd);

        //액션바 숨김
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

        //아이디/패스워드 저장
        SharedPreferences setting;
        final SharedPreferences.Editor editor;
        setting = getSharedPreferences("setting",0);
        editor = setting.edit();
        logincheck = (CheckBox) findViewById(R.id.logincheck);
        logincheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    String ID = loginid.getText().toString();
                    String PW = loginpwd.getText().toString();
                    editor.putString("ID",ID);
                    editor.putString("PW",PW);
                    editor.putBoolean("check",true);
                    editor.commit();
                }
                else {
                    editor.clear();
                    editor.commit();
                }
            }
        });
        if (setting.getBoolean("check",false)){
            loginid.setText(setting.getString("ID",""));
            loginpwd.setText(setting.getString("PW",""));
            logincheck.setChecked(true);
        }

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = loginid.getText().toString();
                String userPassword = loginpwd.getText().toString();

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");
                            if (success){
                                String userID = jsonresponse.getString("userID");
                                String userName = jsonresponse.getString("userName");
                                String userRanking = jsonresponse.getString("userRanking");
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userRanking",userRanking);
                                intent.putExtra("userName", userName);
                                startActivity(intent);
                                finish();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("취소",null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID,userPassword, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });
    }
}
