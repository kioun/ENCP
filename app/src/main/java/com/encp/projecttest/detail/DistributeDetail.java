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
import com.encp.projecttest.request.DistributeDetailRequest;

import org.json.JSONObject;

public class DistributeDetail extends AppCompatActivity {

    TextView upper5pjcName, upper5pjcGroup, upper5userName,
            upper5drafter, upper5payment, upper5review, upper5recipient, upper5title, upper5contents;

    Button upper5btn, upper5btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2_detail);

        upper5drafter = (TextView) findViewById(R.id.upper2drafter);
        upper5pjcName = (TextView) findViewById(R.id.upper2pjcname);
        upper5review = (TextView) findViewById(R.id.upper2review);
        upper5payment = (TextView) findViewById(R.id.upper2payment);
        upper5recipient = (TextView) findViewById(R.id.upper2recipient);
        upper5title = (TextView) findViewById(R.id.upper2title);
        upper5contents = (TextView) findViewById(R.id.upper2contents);
        upper5pjcGroup = (TextView) findViewById(R.id.upper2pjcgroup);
        upper5userName = (TextView) findViewById(R.id.upper2userName);

        Intent intent = getIntent();

        upper5drafter.setText(intent.getStringExtra("drafter"));
        upper5pjcName.setText(intent.getStringExtra("pjcName"));
        upper5review.setText(intent.getStringExtra("review"));
        upper5payment.setText(intent.getStringExtra("payment"));
        upper5recipient.setText(intent.getStringExtra("recipient"));

        upper5title.setText(intent.getStringExtra("title"));
        upper5contents.setText(intent.getStringExtra("contents"));

        upper5pjcGroup.setText(intent.getStringExtra("pjcGroup"));
        upper5userName.setText(intent.getStringExtra("userName"));

        final int number = intent.getIntExtra("noticenumber",1);
        final int count = intent.getIntExtra("distributecount",0);

        upper5btn = (Button) findViewById(R.id.upper2btn);
        upper5btn.setVisibility(View.GONE);
        upper5btn2 = (Button) findViewById(R.id.upper2btn2);
        upper5btn2.setVisibility(View.VISIBLE);

        if (count > 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(DistributeDetail.this);
            builder.setMessage("배포완료된 페이지입니다.")
                    .setPositiveButton("확인",null)
                    .create()
                    .show();
        }
        if (count == 3){
            final int a = Integer.parseInt(UserPage.perrecipientcount);
            final int b = Integer.parseInt(UserPage.percirculationcount);
            upper5btn2.setBackgroundColor(getResources().getColor(R.color.encpcolors));
            upper5btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String drafter = upper5drafter.getText().toString();
                    String pjcName = upper5pjcName.getText().toString();
                    String review = upper5review.getText().toString();
                    String payment = upper5payment.getText().toString();
                    String recipient = upper5recipient.getText().toString();
                    String title = upper5title.getText().toString();
                    String contents = upper5contents.getText().toString();
                    String pjcGroup = upper5pjcGroup.getText().toString();
                    String nbuserName = upper5userName.getText().toString();
                    String userID = MainActivity.userID;
                    int noticenumber = number;
                    int recipientcount = (a - 1);
                    int circulationcount = (b + 1);
                    int distributecount = (count + 1);

                    Response.Listener<String> responselistener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    setResult(1005);
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    DistributeDetailRequest distributeDetailRequest = new DistributeDetailRequest(drafter, pjcName, review, payment, recipient,
                            title, contents, pjcGroup, nbuserName, userID, noticenumber, recipientcount, circulationcount, distributecount, responselistener);
                    RequestQueue queue = Volley.newRequestQueue(DistributeDetail.this);
                    queue.add(distributeDetailRequest);
                }
            });
        }
    }
}
