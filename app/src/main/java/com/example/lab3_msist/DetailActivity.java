package com.example.lab3_msist;

import android.app.Activity;
import android.os.Bundle;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();
    }
}
