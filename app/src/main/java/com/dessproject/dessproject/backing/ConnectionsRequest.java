package com.dessproject.dessproject.backing;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 12/12/2017.
 */



public class ConnectionsRequest extends StringRequest {

    private static final String REGISTER_REQUEST_PULL_URL = "http://pittdssproject.000webhostapp.com/getConnections.php";
    private Map<String, String> params;

    public ConnectionsRequest(int id, Response.Listener<String> listener) {

        super(Request.Method.POST, REGISTER_REQUEST_PULL_URL, listener, null);

        params = new HashMap<>();

        params.put("id", String.valueOf(id));

    }
    @Override
    public Map<String, String> getParams() {

        return params;

    }
}
