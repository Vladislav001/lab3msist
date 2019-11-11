package com.example.lab3_msist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Profile extends Activity{

    private String url= "https://lab1msist.herokuapp.com/profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();


        final Request request = new Request.Builder()
                .url(url)
                .header("x-access-token", token)
                .build();
        new Profile.AsyncHttpRequest().execute(request);

        Log.d("myTokenProfile2",  token);
    }


    @SuppressLint("StaticFieldLeak")
    public class AsyncHttpRequest extends AsyncTask<Request, Void, Response> {
        @Override
        protected Response doInBackground(Request... requests) {
            Response response = null;
            try {
                OkHttpClient client = new OkHttpClient();
                response = client.newCall(requests[0]).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            try {

                String resStr = response.body().string().toString();
                Log.d("myList",  resStr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}