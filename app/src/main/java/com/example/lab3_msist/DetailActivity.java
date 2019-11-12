package com.example.lab3_msist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailActivity extends Activity {

    private String url= "https://lab1msist.herokuapp.com/counter/";
    private TextView detailInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        detailInformation = (TextView) findViewById(R.id.DetailInformation);
        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();
        String id = arguments.get("id").toString();

        final Request request = new Request.Builder()
                .url(url + id)
                .header("x-access-token", token)
                .build();
        new DetailActivity.AsyncHttpRequest().execute(request);
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
                detailInformation.setText(resStr);
                Log.d("myRes",  resStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
