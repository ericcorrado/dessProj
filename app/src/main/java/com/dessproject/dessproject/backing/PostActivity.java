package com.dessproject.dessproject.backing;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import com.dessproject.dessproject.R;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class PostActivity extends AppCompatActivity {
    PostClass newPost;
    PublicKey publicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        newPost = (PostClass) getIntent().getSerializableExtra("post");


        final Button submitButton = (Button) findViewById(R.id.submitBut);
        final EditText contentBox = (EditText) findViewById(R.id.contentBox);
        final EditText titleBox = (EditText) findViewById(R.id.titleBox);

        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String jsonResponse = new String(response);
                    byte[] keyBytes = Base64.decode(jsonResponse.toString().getBytes(),Base64.NO_WRAP);
                    publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));


                }
                catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }


            }

        };
        PublicKeyRequest publicKeyRequest = new PublicKeyRequest(responseListener2);
        RequestQueue queue = Volley.newRequestQueue(PostActivity.this);
        queue.add(publicKeyRequest);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String postToSave =  contentBox.getText().toString();
                EncryptionTools newTools = new EncryptionTools();

                Cipher cipher;
                 byte[] encodedPost = null;






                try{
                    cipher = Cipher.getInstance("RSA");
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                    encodedPost = cipher.doFinal(postToSave.getBytes());
                }
                catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }


                 final String title = titleBox.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Intent intent = new Intent(PostActivity.this, PostActivity.class);
                                        PostActivity.this.startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(PostActivity.this);
                                        builder.setMessage("Post Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                    }

                };

                Date curDate = new Date();
                WallRequest wallRequest = new WallRequest(new String(Base64.encodeToString(encodedPost, android.util.Base64.NO_WRAP)), title, String.valueOf(newPost.getAttatchedTags().get(0)), String.valueOf(newPost.getAttatchedUsers().get(0)),"1", curDate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(PostActivity.this);
                queue.add(wallRequest);

            }

        });
    }
}