package com.example.lab3_msist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddActivity extends Activity {

    private EditText number;
    private EditText data;
    private String url= "https://lab1msist.herokuapp.com/add-counter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        number = (EditText) findViewById(R.id.edit_number);
        data = (EditText) findViewById(R.id.edit_data);

    }

    // Обрабатываем нажатие кнопки "Добавить показания":
    public void Add(View view) {

        Bundle arguments = getIntent().getExtras();
         String token = arguments.get("token").toString();

//        RequestBody formBody = new FormBody.Builder()
//                .add("number", number.getText().toString())
//                .add("data", data.getText().toString())
//                .build();
//
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(formBody)
//                .build();
//        new AuthorizationActivity.AsyncHttpRequest().execute(request);


        Toast.makeText(getApplicationContext(), "токен " + token, Toast.LENGTH_SHORT).show();
    }
}