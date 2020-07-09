package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupValidateRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/userValidate.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/userValidate.php";
    private HashMap<String, String> parameters;

    public SignupValidateRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
