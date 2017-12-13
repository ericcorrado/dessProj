package com.dessproject.dessproject.backing;

/**
 * Created by ramki on 12/10/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UsernameRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://pittdssproject.000webhostapp.com/UsernameCheck.php";
    private Map<String, String> params;

    public UsernameRequest( String username, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
     ;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
