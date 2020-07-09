package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProjectcreateRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/ProjectcreateRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/ProjectcreateRequest.php";
    private HashMap<String, String> parameters;

    public ProjectcreateRequest(String userID, String userName, String pjcName, String pjcGroup, String pjcPosition, String pjcRandom, int pjcBoolean,
                                int draftercount, int reviewcount, int paymentcount, int circulationcount, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userName", userName);
        parameters.put("pjcName",pjcName);
        parameters.put("pjcGroup",pjcGroup);
        parameters.put("pjcPosition",pjcPosition);
        parameters.put("pjcRandom",pjcRandom);
        parameters.put("pjcBoolean", pjcBoolean+"");
        parameters.put("draftercount", draftercount+"");
        parameters.put("reviewcount", reviewcount+"");
        parameters.put("paymentcount", paymentcount+"");
        parameters.put("circulationcount", circulationcount+"");
    }

    public Map<String,String> getParams(){
        return parameters;
    }
}
