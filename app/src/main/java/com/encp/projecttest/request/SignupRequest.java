package com.encp.projecttest.request;

        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

public class SignupRequest extends StringRequest {

//    final static private String URL="http://172.30.1.33/encp/Signup.php";
        final static private String URL="http://blrioun.cafe24.com/encp/php/Signup.php";
    private HashMap<String, String> parameters;

    public SignupRequest(String userID, String userPassword, String userName, String userPosition, String userGroup, String userRanking, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userPosition",userPosition);
        parameters.put("userGroup",userGroup);
        parameters.put("userRanking",userRanking);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
