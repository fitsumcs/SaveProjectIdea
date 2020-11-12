package com.example.saveprojectidea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    TextInputEditText name, email , password;
    Button signUp;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (TextInputEditText) findViewById(R.id.editText_Name);
        email = (TextInputEditText) findViewById(R.id.editTextText_Email);
        password = (TextInputEditText)findViewById(R.id.editTextText_Password);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();






    }

    public void actionSignUp(View view) {

        String Uname = name.getText().toString().trim();
        String Uemail = email.getText().toString().trim();
        String Upassword = password.getText().toString().trim();

        if(TextUtils.isEmpty(Uname) || TextUtils.isEmpty(Uemail) || TextUtils.isEmpty(Upassword))
        {
            Toast.makeText(this,"Please Fill All Fields!!",Toast.LENGTH_SHORT).show();
        }
        else if(Upassword.length() < 6)
        {
            Toast.makeText(this,"Password Length Must be Greater than 6!!",Toast.LENGTH_SHORT).show();
        }
        else {
            registerUser(Uname,Uemail,Upassword);
        }


    }

    private void registerUser(String uname, String uemail, String upassword) {

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(uemail,upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this,"Successfully Registered!!",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    Intent intent = new Intent(SignUp.this,SignIn.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(SignUp.this,"Some thing went Wrong Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}