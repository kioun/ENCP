package com.encp.projecttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.request.SignupRequest;
import com.encp.projecttest.request.SignupValidateRequest;

import org.json.JSONObject;

public class Signup extends AppCompatActivity {

    Button signbutton,validatebutton;
    EditText signid,signpwd,signname;

    boolean validate = false;
    String num = "사용자";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signid = (EditText) findViewById(R.id.signid);
        signpwd = (EditText) findViewById(R.id.signpwd);
        signname = (EditText) findViewById(R.id.signname);

        //중복체크
        validatebutton = (Button) findViewById(R.id.validate);
        validatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = signid.getText().toString();
                if (validate){
                    return;
                }
                if (userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    builder.setMessage("아이디를 입력해주세요.")
                            .setPositiveButton("확인", null)
                            .create();
                    builder.show();
                    return;
                }
                if (userID.length()< 4){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    builder.setMessage("4자리 이상 입력해주세요.")
                            .setPositiveButton("확인",null)
                            .create();
                    builder.show();
                    return;
                }
                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");
                            if (success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                builder.show();
                                signid.setEnabled(false);
                                validate = true;
                                signid.setBackgroundColor(getResources().getColor(R.color.validatecolor));
                                validatebutton.setBackgroundColor(getResources().getColor(R.color.validatecolor));
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                builder.setMessage("사용할 수 없는 아이디입니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                builder.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                };
                SignupValidateRequest signupValidateRequest = new SignupValidateRequest(userID, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Signup.this);
                queue.add(signupValidateRequest);
            }
        });

        signbutton = (Button) findViewById(R.id.signbutton);

        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = signid.getText().toString();
                String userPassword = signpwd.getText().toString();
                String userName = signname.getText().toString();
                String userPosition = "";
                String userGroup = "";
                String userRanking = num;

                if (!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    builder.setMessage("먼저 체크버튼을 눌러주세요.")
                            .setNegativeButton("확인",null)
                            .create();
                    builder.show();
                    return;
                }
                if (userID.equals("") || userPassword.equals("") || userName.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    builder.setMessage("모든 정보를 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    builder.show();
                    return;
                }
                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");
                            if(success){
                                Toast.makeText(Signup.this,"회원가입 되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this,Login.class);
                                intent.putExtra("userRanking",num);
                                startActivity(intent);
                                finish();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                builder.setMessage("회원정보가 맞지 않습니다.")
                                        .setNegativeButton("취소",null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                SignupRequest signupRequest = new SignupRequest(userID,userPassword,userName,userPosition, userGroup, userRanking, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Signup.this);
                queue.add(signupRequest);
            }
        });
    }
}
