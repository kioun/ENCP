package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/Login.php";
      final static private String URL = "http://blrioun.cafe24.com/encp/php/Login.php";
    private HashMap<String, String> parameters;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);

    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
