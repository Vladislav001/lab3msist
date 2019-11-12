package com.example.lab3_msist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

    }

    // Обрабатываем нажатие кнопки "Добавить показания":
    public void Add(View view) {
        Toast.makeText(getApplicationContext(), "ПЫТАЕМСЯ ДОБАВИТЬ", Toast.LENGTH_SHORT).show();
    }
}