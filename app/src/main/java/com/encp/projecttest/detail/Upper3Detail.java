package com.encp.projecttest.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.MainActivity;
import com.encp.projecttest.R;
import com.encp.projecttest.UserPage;
import com.encp.projecttest.request.Upper3DetailRequest;

import org.json.JSONObject;

public class Upper3Detail extends AppCompatActivity {

    TextView upper3pjcName, upper3pjcGroup, upper3userName,
            upper3drafter, upper3payment, upper3review, upper3recipient, upper3title, upper3contents;

    Button upper3btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2_detail);

        upper3drafter = (TextView) findViewById(R.id.upper2drafter);
        upper3pjcName = (TextView) findViewById(R.id.upper2pjcname);
        upper3review = (TextView) findViewById(R.id.upper2review);
        upper3payment = (TextView) findViewById(R.id.upper2payment);
        upper3recipient = (TextView) findViewById(R.id.upper2recipient);
        upper3title = (TextView) findViewById(R.id.upper2title);
        upper3contents = (TextView) findViewById(R.id.upper2contents);
        upper3pjcGroup = (TextView) findViewById(R.id.upper2pjcgroup);
        upper3userName = (TextView) findViewById(R.id.upper2userName);

        Intent intent = getIntent();

        upper3drafter.setText(intent.getStringExtra("drafter"));
        upper3pjcName.setText(intent.getStringExtra("pjcName"));
        upper3review.setText(intent.getStringExtra("review"));
        upper3payment.setText(intent.getStringExtra("payment"));
        upper3recipient.setText(intent.getStringExtra("recipient"));

        upper3title.setText(intent.getStringExtra("title"));
        upper3contents.setText(intent.getStringExtra("contents"));

        upper3pjcGroup.setText(intent.getStringExtra("pjcGroup"));
        upper3userName.setText(intent.getStringExtra("userName"));

        final int number = intent.getIntExtra("noticenumber",1);
        final int count = intent.getIntExtra("distributecount",0);

        if (count > 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(Upper3Detail.this);
            builder.setMessage("확인완료된 페이지입니다.")
                    .setPositiveButton("확인",null)
                    .create()
                    .show();
        }
        if (count == 1 || upper3review == upper3userName) {
            upper3btn = (Button) findViewById(R.id.upper2btn);
            upper3btn.setBackgroundColor(getResources().getColor(R.color.encpcolors));
            upper3btn.setEnabled(true);

            final int b = Integer.parseInt(UserPage.perreviewcount);
            upper3btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String drafter = upper3drafter.getText().toString();
                    String pjcName = upper3pjcName.getText().toString();
                    String review = upper3review.getText().toString();
                    String payment = upper3payment.getText().toString();
                    String recipient = upper3recipient.getText().toString();
                    String title = upper3title.getText().toString();
                    String contents = upper3contents.getText().toString();
                    String pjcGroup = upper3pjcGroup.getText().toString();
                    String nbuserName = upper3userName.getText().toString();
                    String userID = MainActivity.userID;
                    int noticenumber = number;
                    int reviewcount = (b - 1);
                    int distributecount = (count + 1);

                    Response.Listener<String> responselistener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
//                                    Intent intent = new Intent(Upper3Detail.this,MainActivity.class);
                                    setResult(1003);
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Upper3DetailRequest upper3DetailRequest = new Upper3DetailRequest(drafter, pjcName, review, payment, recipient,
                            title, contents, pjcGroup, nbuserName, userID, reviewcount, distributecount, noticenumber, responselistener);
                    RequestQueue queue = Volley.newRequestQueue(Upper3Detail.this);
                    queue.add(upper3DetailRequest);
                }
            });
        }
    }
}
