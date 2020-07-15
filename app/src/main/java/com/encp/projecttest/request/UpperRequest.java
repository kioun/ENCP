package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpperRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/UpperRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/UpperRequest.php";
    private HashMap<String, String> parameters;

    public UpperRequest(String userID, String pjcName, String pjcGroup, String nbuserName,
                        String drafter, String draftergroup, String drafterposition, int dfdraftercount,
                        String review, String reviewgroup, String reviewposition, int rvreviewcount,
                        String payment, String paymentgroup, String paymentposition, int pmpaymentcount,
                        String recipient, String recipientgroup, String recipientposition,
                        String title, String contents,
                        int distributecount, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("pjcName", pjcName);
        parameters.put("pjcGroup", pjcGroup);
        parameters.put("nbuserName",nbuserName);
        parameters.put("drafter", drafter);
        parameters.put("draftergroup", draftergroup);
        parameters.put("drafterposition",drafterposition);
        parameters.put("dfdraftercount", dfdraftercount+"");
        parameters.put("review", review);
        parameters.put("reviewgroup", reviewgroup);
        parameters.put("reviewposition", reviewposition);
        parameters.put("rvreviewcount", rvreviewcount+"");
        parameters.put("payment", payment);
        parameters.put("paymentgroup", paymentgroup);
        parameters.put("paymentposition", paymentposition);
        parameters.put("pmpaymentcount", pmpaymentcount+"");
        parameters.put("recipient", recipient);
        parameters.put("recipientgroup", recipientgroup);
        parameters.put("recipientposition",recipientposition);
        parameters.put("title", title);
        parameters.put("contents",contents);
        parameters.put("distributecount", distributecount+"");
    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
