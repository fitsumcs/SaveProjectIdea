package com.example.saveprojectidea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    TextView editText_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

         editText_signup = (TextView) findViewById(R.id.tv_gotoSignUp);


    }


    public void goToSignUp(View view) {
        Intent intent = new Intent(SignIn.this,SignUp.class);
        startActivity(intent);
    }
}