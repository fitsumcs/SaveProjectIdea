package com.example.saveprojectidea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText name, email , password;
    Button signUp;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.editText_Name);
        email = (EditText) findViewById(R.id.editTextText_Email);
        password = (EditText)findViewById(R.id.editTextText_Password);
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
            Toast.makeText(this,"Password Lengeth Must be Greater than 6!!",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(SignUp.this,"Successfully Regesterd!!",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    Intent intent = new Intent(SignUp.this,SignIn.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(SignUp.this,"Some thing get Wrong Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}