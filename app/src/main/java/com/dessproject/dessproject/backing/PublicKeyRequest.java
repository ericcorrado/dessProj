package com.dessproject.dessproject.backing;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import android.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 12/13/2017.
 */

public class PublicKeyRequest extends StringRequest{

    private static final String REGISTER_REQUEST_READ_URL = "http://pittdssproject.000webhostapp.com/publicKeyRead.php";
    private static final String REGISTER_REQUEST_WRITE_URL = "http://pittdssproject.000webhostapp.com/publicKeyWrite.php";
    private Map<String, String> params;

    public PublicKeyRequest(byte[] key,Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_WRITE_URL, listener, null);

        params = new HashMap<>();

        params.put("key",new String(Base64.encodeToString(key, android.util.Base64.NO_WRAP)));
    }
    public PublicKeyRequest(Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_READ_URL, listener, null);

    }
    public Map<String, String> getParams() {

        return params;

    }
}
