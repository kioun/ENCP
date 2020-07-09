package com.encp.projecttest;

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
import com.encp.projecttest.request.CirculationRequest;

import org.json.JSONObject;

public class Circulation extends AppCompatActivity {

    TextView ccpjcName, ccuserName;
    EditText ccpjcGroup, ccrecipient, cctitle, cccontents;
    Button ccbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulation);

        ccpjcName = (TextView) findViewById(R.id.ccpjcName);
        ccuserName = (TextView) findViewById(R.id.ccuserName);

        ccpjcGroup = (EditText) findViewById(R.id.ccpjcGroup);
        ccrecipient = (EditText) findViewById(R.id.ccrecipient);
        cctitle = (EditText) findViewById(R.id.cctitle);
        cccontents = (EditText) findViewById(R.id.cccontents);

        ccpjcName.setText(UserPage.perName);
        ccuserName.setText(MainActivity.userName);


        ccbtn = (Button) findViewById(R.id.ccbtn);
        ccbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;
                String pjcName = ccpjcName.getText().toString();
                String pjcGroup = ccpjcGroup.getText().toString();
                String userName = ccuserName.getText().toString();
                String recipient = ccrecipient.getText().toString();
                String title = cctitle.getText().toString();
                String contents = cccontents.getText().toString();
                String circulation1 = "대기중";

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(Circulation.this,"성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Circulation.this,MainActivity.class);
                                setResult(1000,intent);
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                CirculationRequest circulationRequest = new CirculationRequest(userID, pjcName, pjcGroup, userName, recipient, title,
                        contents, circulation1, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Circulation.this);
                queue.add(circulationRequest);
            }
        });
    }
}
