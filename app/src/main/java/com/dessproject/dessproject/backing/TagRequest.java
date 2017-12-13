package com.dessproject.dessproject.backing;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 12/12/2017.
 */

public class TagRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://pittdssproject.000webhostapp.com/getTags.php";


    public TagRequest(Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);



    }


}
