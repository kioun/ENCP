package com.encp.projecttest.detail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.encp.projecttest.MainActivity;
import com.encp.projecttest.R;
import com.encp.projecttest.UserPage;
import com.encp.projecttest.request.Upper2DetailRequest;

import org.json.JSONObject;

public class Upper2Detail extends AppCompatActivity {

    TextView upper2pjcName, upper2pjcGroup, upper2userName,
            upper2drafter, upper2payment, upper2review, upper2recipient, upper2title, upper2contents;
    Button upper2btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper2_detail);

        upper2drafter = (TextView) findViewById(R.id.upper2drafter);
        upper2pjcName = (TextView) findViewById(R.id.upper2pjcname);
        upper2review = (TextView) findViewById(R.id.upper2review);
        upper2payment = (TextView) findViewById(R.id.upper2payment);
        upper2recipient = (TextView) findViewById(R.id.upper2recipient);
        upper2title = (TextView) findViewById(R.id.upper2title);
        upper2contents = (TextView) findViewById(R.id.upper2contents);
        upper2pjcGroup = (TextView) findViewById(R.id.upper2pjcgroup);
        upper2userName = (TextView) findViewById(R.id.upper2userName);

        Intent intent = getIntent();

        upper2drafter.setText(intent.getStringExtra("drafter"));
        upper2pjcName.setText(intent.getStringExtra("pjcName"));
        upper2review.setText(intent.getStringExtra("review"));
        upper2payment.setText(intent.getStringExtra("payment"));
        upper2recipient.setText(intent.getStringExtra("recipient"));

        upper2title.setText(intent.getStringExtra("title"));
        upper2contents.setText(intent.getStringExtra("contents"));

        upper2pjcGroup.setText(intent.getStringExtra("pjcGroup"));
        upper2userName.setText(intent.getStringExtra("userName"));

        final int number = intent.getIntExtra("noticenumber",1);
        final int count = intent.getIntExtra("distributecount",0);

        if (count > 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(Upper2Detail.this);
            builder.setMessage("확인완료된 페이지입니다.")
                    .setPositiveButton("확인",null)
                    .create()
                    .show();
        }
        if (count == 0 || upper2drafter == upper2userName) {
            upper2btn = (Button) findViewById(R.id.upper2btn);
            upper2btn.setBackgroundColor(getResources().getColor(R.color.encpcolors));
            upper2btn.setEnabled(true);

            final int a = Integer.parseInt(UserPage.perdraftercount);
            upper2btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String drafter = upper2drafter.getText().toString();
                    String pjcName = upper2pjcName.getText().toString();
                    String review = upper2review.getText().toString();
                    String payment = upper2payment.getText().toString();
                    String recipient = upper2recipient.getText().toString();
                    String title = upper2title.getText().toString();
                    String contents = upper2contents.getText().toString();
                    String pjcGroup = upper2pjcGroup.getText().toString();
                    String nbuserName = upper2userName.getText().toString();
                    String userID = MainActivity.userID;
                    int noticenumber = number;
                    int draftercount = (a - 1);
                    int distributecount = (count + 1);

                    Response.Listener<String> responselistener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    setResult(1002);
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Upper2DetailRequest upper2DetailRequest = new Upper2DetailRequest(drafter, pjcName, review, payment, recipient,
                            title, contents, pjcGroup, nbuserName, userID, draftercount, distributecount, noticenumber, responselistener);
                    RequestQueue queue = Volley.newRequestQueue(Upper2Detail.this);
                    queue.add(upper2DetailRequest);
                }
            });
        }
    }
}
