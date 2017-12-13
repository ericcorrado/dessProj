package com.dessproject.dessproject.backing;

import android.util.Base64;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 12/13/2017.
 */

public class PrivateKeyRequest extends StringRequest{
    private static final String REGISTER_REQUEST_READ_URL = "http://pittdssproject.000webhostapp.com/privateKeyRead.php";
    private static final String REGISTER_REQUEST_WRITE_URL = "http://pittdssproject.000webhostapp.com/privateKeyWrite.php";
    private Map<String,String> params;

    public PrivateKeyRequest(byte[] key,Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_WRITE_URL, listener, null);

        params = new HashMap<>();

        params.put("key",new String(Base64.encodeToString(key,Base64.NO_WRAP)));
    }
    public PrivateKeyRequest(Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_READ_URL, listener, null);

    }
    public Map<String, String> getParams() {

        return params;

    }
}
