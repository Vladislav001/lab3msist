package com.example.lab3_msist;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
            Log.d("myRes33", Objects.requireNonNull(response.body()).string());
            //txtRequest.setText(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
