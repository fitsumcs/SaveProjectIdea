package com.example.saveprojectidea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {


    TextInputEditText email,password;
    TextView editText_signup;
    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (TextInputEditText) findViewById(R.id.editText_Email);
        password = (TextInputEditText)findViewById(R.id.editText_Password);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        editText_signup = (TextView) findViewById(R.id.tv_gotoSignUp);



    }


    public void goToSignUp(View view) {
        Intent intent = new Intent(SignIn.this,SignUp.class);
        startActivity(intent);
    }

    public void actionSignIn(View view) {
        String Uemail = email.getText().toString().trim();
        String Upassword = password.getText().toString().trim();
        if(TextUtils.isEmpty(Uemail) || TextUtils.isEmpty(Upassword))
        {
            Toast.makeText(this,"Please Fill All Fields!!",Toast.LENGTH_SHORT).show();
        }
        else if(Upassword.length() < 6)
        {
            Toast.makeText(this,"Password Length Must be Greater than 6!!",Toast.LENGTH_SHORT).show();
        }
        else {
            loginUser(Uemail,Upassword);
        }

    }

    private void loginUser(String email, String password) {

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful())
                {
                    Intent intent = new Intent(SignIn.this,HomePage.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(SignIn.this,"Some thing went Wrong Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}