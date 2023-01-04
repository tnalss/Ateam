package com.example.lastproject.login;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.lastproject.R;


public class LogoutActivity extends AppCompatActivity {
    SharedPreferences preferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("auto_login", false);
        editor.putString("auto_login_id", ""  );
        editor.putString("auto_login_pw", "");
        editor.commit(); //완료한다.
        Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
        startActivity(intent);

    }

}
