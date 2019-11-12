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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// http://developer.alexanderklimov.ru/android/views/listview.php

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




                // получаем экземпляр элемента ListView
                ListView listView = findViewById(R.id.listView);


                final String[] catNames = new String[] {
                        "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
                        "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
                        "Китти", "Масяня", "Симба"
                };

                // используем адаптер данных
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Profile.this,
                        android.R.layout.simple_list_item_1, catNames);

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                            long id) {
                        Intent intent = new Intent(Profile.this, DetailActivity.class);
                        // конкретные данные
                        startActivity(intent);

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}