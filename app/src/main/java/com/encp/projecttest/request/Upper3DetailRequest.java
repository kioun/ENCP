package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Upper3DetailRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/Upper3DetailRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/Upper3DetailRequest.php";
    private HashMap<String, String> parameters;

    public Upper3DetailRequest(String drafter, String pjcName, String review, String payment, String recipient,
                               String title, String contents, String pjcGroup, String nbuserName, String userID,
                               int reviewcount, int distributecount, int noticenumber, Response.Listener<String> listener){
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
        parameters.put("reviewcount", reviewcount+"");
        parameters.put("distributecount", distributecount+"");
        parameters.put("noticenumber", noticenumber+"");
    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
