package com.example.lab3_msist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// http://developer.alexanderklimov.ru/android/views/listview.php

public class ProfileActivity extends Activity{

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
        new ProfileActivity.AsyncHttpRequest().execute(request);

    }


    // Обрабатываем нажатие кнопки "Добавить показания":
    public void AddCounter(View view) {
        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();

        Intent intent = new Intent(ProfileActivity.this, AddActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
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

                String resStr = response.body().string();
                Log.d("myRes",  resStr);

                Data[] data = new Gson().fromJson(resStr, Data[].class);

                final String[] ids = new String[data.length];

                for(int i = 0; i < data.length; i++){
                    ids[i] = data[i]._id;
                }

                    // получаем экземпляр элемента ListView
                    ListView listView = findViewById(R.id.listView);

                    // используем адаптер данных
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ProfileActivity.this,
                            android.R.layout.simple_list_item_1, ids);

                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                                long id) {
                            Bundle arguments = getIntent().getExtras();
                            String token = arguments.get("token").toString();

                            TextView textView = (TextView) itemClicked;
                            String idCounter = textView.getText().toString(); // получаем id нажатого элемента

                            Intent intent = new Intent(ProfileActivity.this, DetailActivity.class);
                            intent.putExtra("token", token);
                            intent.putExtra("id", idCounter);
                            startActivity(intent);

                        }
                    });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class Data {
         String user_id;
         String _id;
         Number number;
         String type;
         String place;
         String data;
         String date_completion;
         String _v;

//        public String getUserId() { return user_id; }
//        public Number getNumber() { return number; }
//
//
//        public void setUserId(String user_id) { this.user_id = user_id; }
//        public void setNumber(Number number) { this.number = number; }


//        public String toString() {
//            return String.format("title:%s,id:%d,children:%s,groups:%s", title, id, children, groups);
//        }
    }
}