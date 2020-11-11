package com.example.saveprojectidea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText name, email , password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.editText_Name);
        email = (EditText) findViewById(R.id.editTextText_Email);
        password = (EditText)findViewById(R.id.editTextText_Password);






    }

    public void actionSignUp(View view) {

        String Uname = name.getText().toString();
        String Uemail = email.getText().toString();
        String Upassword = password.getText().toString();

        if(TextUtils.isEmpty(Uname) || TextUtils.isEmpty(Uemail) || TextUtils.isEmpty(Upassword))
        {
            Toast.makeText(this,"Please Fill All Fields!!",Toast.LENGTH_SHORT).show();
        }
        else {
            registerUser(Uname,Uemail,Upassword);
        }


    }

    private void registerUser(String uname, String uemail, String upassword) {
    }
}