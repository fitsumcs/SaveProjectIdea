package com.example.saveprojectidea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {


    TextInputEditText email;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        progressDialog = new ProgressDialog(this);
        email = (TextInputEditText) findViewById(R.id.editText_Email);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void actionResetPassword(View view) {
        String Uemail = email.getText().toString().trim();
        if(TextUtils.isEmpty(Uemail) )
        {
            Toast.makeText(this,"Please Fill Your Email!!",Toast.LENGTH_SHORT).show();
        }
        else {
            sendResetEmail(Uemail);
        }


    }

    private void sendResetEmail(String uemail) {

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(uemail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss();

                if(task.isSuccessful())
                {
                    Toast.makeText(ResetPassword.this,"Please Check your Mail!!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetPassword.this,SignIn.class);
                    startActivity(intent);

                }
                else{
                    String errMesg = task.getException().getMessage();
                    Toast.makeText(ResetPassword.this,errMesg,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}