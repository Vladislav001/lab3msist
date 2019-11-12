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
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddActivity extends Activity {

    private EditText number;
    private EditText data;
    private String type;
    private String place;
    private String url= "https://lab1msist.herokuapp.com/add-counter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        number = (EditText) findViewById(R.id.edit_number);
        data = (EditText) findViewById(R.id.edit_data);


        final Spinner typeCounterSpinner = (Spinner) findViewById(R.id.edit_type);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_counter, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeCounterSpinner.setAdapter(adapter);

        typeCounterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Spinner placeSpinner = (Spinner) findViewById(R.id.edit_place);
        final ArrayAdapter<CharSequence> adapterPlace = ArrayAdapter.createFromResource(this,
                R.array.place, android.R.layout.simple_spinner_item);
        adapterPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(adapterPlace);

        placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    // Обрабатываем нажатие кнопки "Добавить показания":
    public void Add(View view) {

        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();

        RequestBody formBody = new FormBody.Builder()
                .add("number", number.getText().toString())
                .add("data", data.getText().toString())
                .add("type", type)
                .add("place", place)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .header("x-access-token", token)
                .post(formBody)
                .build();
        new AddActivity.AsyncHttpRequest().execute(request);


        Toast.makeText(getApplicationContext(), "Показания успешно добавлены", Toast.LENGTH_SHORT).show();
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

                Log.d("myRes", resStr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}