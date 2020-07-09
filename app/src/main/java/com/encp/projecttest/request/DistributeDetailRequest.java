package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DistributeDetailRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/Upper5DetailRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/Upper5DetailRequest.php";
    private HashMap<String, String> parameters;

    public DistributeDetailRequest(String drafter, String pjcName, String review, String payment, String recipient,
                                   String title, String contents, String pjcGroup, String nbuserName, String userID,
                                   int noticenumber, int recipientcount, int circulationcount, int distributecount, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("drafter", drafter);
        parameters.put("pjcName", pjcName);
        parameters.put("review", review);
        parameters.put("payment", payment);
        parameters.put("userName", recipient);
        parameters.put("title", title);
        parameters.put("contents", contents);
        parameters.put("pjcGroup", pjcGroup);
        parameters.put("nbuserName", nbuserName);
        parameters.put("userID", userID);
        parameters.put("noticenumber", noticenumber+"");
        parameters.put("recipientcount", recipientcount+"");
        parameters.put("circulationcount", circulationcount+"");
        parameters.put("distributecount", distributecount+"");
    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
