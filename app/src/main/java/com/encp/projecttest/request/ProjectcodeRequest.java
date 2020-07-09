package com.encp.projecttest.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProjectcodeRequest extends StringRequest {

//    final static private String URL = "http://172.30.1.33/encp/pjcodeRequest.php";
    final static private String URL = "http://blrioun.cafe24.com/encp/php/pjcodeRequest.php";
    private HashMap<String, String> parameters;

    public ProjectcodeRequest(String userID, int pjcRandom, String userName, String pjcName, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("pjcRandom",pjcRandom+"");
        parameters.put("userName", userName);
        parameters.put("pjcName", pjcName);

    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
