package com.example.saveprojectidea;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (user != null) {
                    Intent intent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(MainActivity.this,SignIn.class);
                    startActivity(intent);
                    finish();
                }



            }
        },3000);




    }

    


}