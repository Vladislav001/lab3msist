package com.example.lab3_msist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Profile extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();
        Log.d("myTokenProfile2",  token);
    }
}