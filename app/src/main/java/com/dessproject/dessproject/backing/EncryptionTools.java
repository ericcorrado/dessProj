package com.dessproject.dessproject.backing;

/**
 * Created by Eric on 12/13/2017.
 */


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class EncryptionTools extends AppCompatActivity {
    private static final String REGISTER_REQUEST_URL = "http://pittdssproject.000webhostapp.com/keys.txt";

    public PrivateKey getPrivateKey() {

        try {
            getKeyFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    PrivateKey privateKey;

    public PublicKey getPublicKey() {
        try {
            getPubKeyFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    PublicKey publicKey;

    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
      /* KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(2048);
        KeyPair kpair = kpg.genKeyPair();

        byte[] publicKeyBytes = kpair.getPublic().getEncoded();
        byte[] privateKeyBytes = kpair.getPrivate().getEncoded();

        sendToServer(privateKeyBytes);
        sendPubToServer(publicKeyBytes);*/






    }


    public void sendToServer(byte[] privateKeyBytes){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        PrivateKeyRequest privateKeyRequest = new PrivateKeyRequest(privateKeyBytes, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EncryptionTools.this);
        queue.add(privateKeyRequest);
    }
    public void getKeyFromFile() throws Exception{


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String jsonResponse = new String(response);

                    byte[] keyBytes = Base64.decode(jsonResponse.toString().getBytes(),Base64.NO_WRAP);

                    privateKey =  KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
                    System.out.print(privateKey);

                }
                catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                 catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }


            }

        };
        PrivateKeyRequest privateKeyRequest = new PrivateKeyRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(EncryptionTools.this);
        queue.add(privateKeyRequest);

    }
    public void sendPubToServer(byte[] publicKeyBytes){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonResponse = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        };
        PublicKeyRequest publicKeyRequest = new PublicKeyRequest(publicKeyBytes, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EncryptionTools.this);
        queue.add(publicKeyRequest);
    }
    public void getPubKeyFromFile() throws Exception{


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String jsonResponse = new String(response);
                    byte[] keyBytes = Base64.decode(jsonResponse.toString().getBytes(),Base64.NO_WRAP);
                     publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
                    System.out.print(publicKey);

                }
                catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }


            }

        };
        PublicKeyRequest publicKeyRequest = new PublicKeyRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(EncryptionTools.this);
        queue.add(publicKeyRequest);

    }

}
