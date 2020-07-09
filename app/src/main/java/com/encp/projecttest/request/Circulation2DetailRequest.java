package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Circulation2DetailRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/cc2detailRequest.php";
        final static private String URL = "http://blrioun.cafe24.com/encp/php/cc2detailRequest.php";

    private HashMap<String, String> parameters;

    public Circulation2DetailRequest(String circulation1,
                                     String pjcName, String pjcGroup, String recipient, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("circulation1",circulation1);
        parameters.put("pjcName", pjcName);
        parameters.put("pjcGroup",pjcGroup);
        parameters.put("recipient",recipient);

    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
