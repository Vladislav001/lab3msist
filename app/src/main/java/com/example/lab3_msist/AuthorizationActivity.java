package com.example.lab3_msist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

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
    public String url= "https://lab1msist.herokuapp.com/profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        email = (EditText) findViewById(R.id.edit_email);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        mTextViewResult = findViewById(R.id.text_view_result);

        final Request request = new Request.Builder()
                .url(url).get()
                .build();
        new AsyncHttpRequest().execute(request);


    }


    // Обрабатываем нажатие кнопки "Войти":
    public void Login(View view) {


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

}


