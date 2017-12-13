package com.dessproject.dessproject.backing;

/**
 * Created by Eric on 12/3/2017.
 */


import com.android.volley.Response;

import com.android.volley.toolbox.StringRequest;


import java.util.Date;
import java.util.HashMap;

import java.util.Map;



public class WallRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://pittdssproject.000webhostapp.com/PostInsert.php";
    private static final String REGISTER_REQUEST_PULL_URL = "http://pittdssproject.000webhostapp.com/WallRequest.php";
    private Map<String, String> params;



    public WallRequest(String post,String title, String tId,String aId, Date dateTime, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();

        params.put("post", post);

        params.put("title", title);

        params.put("tId", tId);

        params.put("aId", aId);

        params.put("date", dateTime.toString());



    }
    public WallRequest(int id, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_PULL_URL, listener, null);

        params = new HashMap<>();

        params.put("id", String.valueOf(id));

    }




    @Override

    public Map<String, String> getParams() {

        return params;

    }

}
