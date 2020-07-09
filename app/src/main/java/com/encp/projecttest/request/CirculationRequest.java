package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CirculationRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/CirculationRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/CirculationRequest.php";
    private HashMap<String, String> parameters;

    public CirculationRequest(String userID, String pjcName, String pjcGroup, String userName,
                              String recipient, String title, String contents, String circulation1, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("pjcName", pjcName);
        parameters.put("pjcGroup", pjcGroup);
        parameters.put("userName", userName);
        parameters.put("recipient", recipient);
        parameters.put("title", title);
        parameters.put("contents", contents);
        parameters.put("circulation1", circulation1);
    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
