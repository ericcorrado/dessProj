package com.dessproject.dessproject.backing; /**
 * Created by ramki on 12/5/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://pittdssproject.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String FName, String LName, String Email, String username, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("FName", FName);
        params.put("LName", LName);
        params.put("Email", Email);
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
