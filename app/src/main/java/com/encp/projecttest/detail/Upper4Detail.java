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
import com.encp.projecttest.request.Upper4DetailRequest;

import org.json.JSONObject;

public class Upper4Detail extends AppCompatActivity {

    TextView upper4pjcName, upper4pjcGroup, upper4userName,
            upper4drafter, upper4payment, upper4review, upper4recipient, upper4title, upper4contents;

    TextView sucdrafter;
    Button upper4btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2_detail);

        upper4drafter = (TextView) findViewById(R.id.upper2drafter);
        upper4pjcName = (TextView) findViewById(R.id.upper2pjcname);
        upper4review = (TextView) findViewById(R.id.upper2review);
        upper4payment = (TextView) findViewById(R.id.upper2payment);
        upper4recipient = (TextView) findViewById(R.id.upper2recipient);
        upper4title = (TextView) findViewById(R.id.upper2title);
        upper4contents = (TextView) findViewById(R.id.upper2contents);
        upper4pjcGroup = (TextView) findViewById(R.id.upper2pjcgroup);
        upper4userName = (TextView) findViewById(R.id.upper2userName);

        Intent intent = getIntent();

        upper4drafter.setText(intent.getStringExtra("drafter"));
        upper4pjcName.setText(intent.getStringExtra("pjcName"));
        upper4review.setText(intent.getStringExtra("review"));
        upper4payment.setText(intent.getStringExtra("payment"));
        upper4recipient.setText(intent.getStringExtra("recipient"));

        upper4title.setText(intent.getStringExtra("title"));
        upper4contents.setText(intent.getStringExtra("contents"));

        upper4pjcGroup.setText(intent.getStringExtra("pjcGroup"));
        upper4userName.setText(intent.getStringExtra("userName"));

        final int number = intent.getIntExtra("noticenumber",1);
        final int count = intent.getIntExtra("distributecount",0);

        if (count > 2){
            AlertDialog.Builder builder = new AlertDialog.Builder(Upper4Detail.this);
            builder.setMessage("확인완료된 페이지입니다.")
                    .setPositiveButton("확인",null)
                    .create()
                    .show();
        }
        if (count == 2 || upper4payment == upper4userName) {
            upper4btn = (Button) findViewById(R.id.upper2btn);
            upper4btn.setBackgroundColor(getResources().getColor(R.color.encpcolors));
            upper4btn.setEnabled(true);

            final int c = Integer.parseInt(UserPage.perpaymentcount);
            final int c2 = Integer.parseInt(UserPage.perrecipientcount);
            upper4btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String drafter = upper4drafter.getText().toString();
                    String pjcName = upper4pjcName.getText().toString();
                    String review = upper4review.getText().toString();
                    String payment = upper4payment.getText().toString();
                    String recipient = upper4recipient.getText().toString();
                    String title = upper4title.getText().toString();
                    String contents = upper4contents.getText().toString();
                    String pjcGroup = upper4pjcGroup.getText().toString();
                    String nbuserName = upper4userName.getText().toString();
                    String userID = MainActivity.userID;
                    int noticenumber = number;
                    int paymentcount = (c - 1);
                    int distributecount = (count + 1);
                    int recipientcount = (c2 + 1);

                    Response.Listener<String> responselistener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    setResult(1004);
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Upper4DetailRequest upper4DetailRequest = new Upper4DetailRequest(drafter, pjcName, review, payment, recipient,
                            title, contents, pjcGroup, nbuserName, userID, paymentcount, distributecount, noticenumber, recipientcount, responselistener);
                    RequestQueue queue = Volley.newRequestQueue(Upper4Detail.this);
                    queue.add(upper4DetailRequest);
                }
            });
        }
    }
}
