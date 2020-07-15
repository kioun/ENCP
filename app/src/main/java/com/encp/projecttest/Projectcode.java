package com.encp.projecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.encp.projecttest.request.ProjectcodeRequest;

import org.json.JSONObject;

public class Projectcode extends AppCompatActivity {

    EditText codenum,codename;
    TextView codetest;
    Button codebtnapply;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectcode);

        codenum = (EditText) findViewById(R.id.codenum);
        codename = (EditText) findViewById(R.id.codename);
        codetest = (TextView) findViewById(R.id.codetest);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        code = intent.getStringExtra("perRandom");
        codetest.setText(code);
        codebtnapply = (Button) findViewById(R.id.codeapply);
        codebtnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;
                int pjcRandom = Integer.parseInt(codenum.getText().toString());
                String userName = MainActivity.userName;
                String pjcName = codename.getText().toString();

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(Projectcode.this,"성공",Toast.LENGTH_SHORT).show();
                                setResult(101);
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                ProjectcodeRequest projectcodeRequest = new ProjectcodeRequest(userID, pjcRandom, userName, pjcName, responselistener);
                RequestQueue queue = Volley.newRequestQueue(Projectcode.this);
                queue.add(projectcodeRequest);
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
