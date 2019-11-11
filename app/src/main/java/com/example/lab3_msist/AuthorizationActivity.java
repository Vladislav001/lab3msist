package com.example.lab3_msist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AuthorizationActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private TextView mTextViewResult;
    public String url= "https://lab1msist.herokuapp.com/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        email = (EditText) findViewById(R.id.edit_email);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        mTextViewResult = findViewById(R.id.text_view_result);
    }


    // Обрабатываем нажатие кнопки "Войти":
    public void Login(View view) {

        RequestBody formBody = new FormBody.Builder()
                .add("email", email.getText().toString())
                .add("password", password.getText().toString())
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        new AsyncHttpRequest().execute(request);

        // Если введенные логин и пароль будут словом "admin",
        // показываем Toast сообщение об успешном входе:
//        if (email.getText().toString().equals("admin") &&
//                password.getText().toString().equals("admin")) {
//            Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
//
//            // Выполняем переход на другой экран:
//            Intent intent = new Intent(AuthorizationActivity.this, Profile.class);
//            startActivity(intent);
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
//        }
    }

//    @SuppressLint("StaticFieldLeak")
//    class AsyncHttpRequest extends AsyncTask<Request, Void, Response> {
//
//        @Override
//        protected Response doInBackground(Request... requests) {
//            Response response = null;
//            try {
//                OkHttpClient client = new OkHttpClient();
//                response = client.newCall(requests[0]).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(Response response) {
//            super.onPostExecute(response);
//            try {
//                Log.d("myRes", Objects.requireNonNull(response.body()).string());
//                //txtRequest.setText(response.body().string());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

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
                try {
                    JSONObject json = new JSONObject(resStr);

                    if (json.has("token")) {
                        Intent intent = new Intent(AuthorizationActivity.this, Profile.class);
                        intent.putExtra("token", json.get("token").toString());
                        startActivity(intent);
                    } else if(json.has("errors")){
                        JSONArray jsonArrayErrors = json.getJSONArray("errors");
                        JSONObject error = new JSONObject(jsonArrayErrors.get(0).toString());
                        Toast.makeText(getApplicationContext(), error.get("message").toString(), Toast.LENGTH_SHORT).show();
                    }

//                    Log.d("myJson",  json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

              // Log.d("myRes", "ad");
                //txtRequest.setText(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


