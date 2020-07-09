package com.encp.projecttest.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.encp.projecttest.R;

public class UpperStatusDetail extends AppCompatActivity {

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
            AlertDialog.Builder builder = new AlertDialog.Builder(UpperStatusDetail.this);
            builder.setMessage("확인완료된 페이지입니다.")
                    .setPositiveButton("확인",null)
                    .create()
                    .show();
        }
        upper2btn = (Button) findViewById(R.id.upper2btn);
        upper2btn.setVisibility(View.INVISIBLE);
    }
}
