package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DetailRequest extends StringRequest {


//    final static private String URL = "http://172.30.1.33/encp/DetailRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/DetailRequest.php";
    private HashMap<String, String> parameters;

    public DetailRequest(String userID,String pjcPosition, String pjcGroup, int pjcBoolean, int draftercount, int reviewcount, int paymentcount, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("pjcPosition",pjcPosition);
        parameters.put("pjcGroup",pjcGroup);
        parameters.put("pjcBoolean", pjcBoolean+"");
        parameters.put("draftercount", draftercount+"");
        parameters.put("reviewcount", reviewcount+"");
        parameters.put("paymentcount", paymentcount+"");

    }

    public Map<String , String> getParams(){
        return parameters;
    }
}
