package com.example.saveprojectidea;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Add_Idea extends Fragment {

    EditText title,description;
    String Utitle , Udescription;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference projectDatabase;

    public Add_Idea() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__idea, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = (EditText) view.findViewById(R.id.editTextTitle);
        description = (EditText) view.findViewById(R.id.editTextDescription);

        progressDialog = new ProgressDialog(getContext());

        firebaseAuth = FirebaseAuth.getInstance();

        projectDatabase = FirebaseDatabase.getInstance().getReference("Projects");



        Button button = (Button)view.findViewById(R.id.button_addIdea);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utitle = title.getText().toString();
                Udescription = description.getText().toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("\"EEE, dd MMM yyyy\"");
                Calendar calendar = Calendar.getInstance();
                String today = simpleDateFormat.format(calendar.getTime());

                //get user
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String userId = firebaseUser.getUid();

                //random key
                String key = projectDatabase.push().getKey();

                if(TextUtils.isEmpty(Utitle) || TextUtils.isEmpty(Udescription))
                {
                    Toast.makeText(getContext(),"Please Fill All Fields!!",Toast.LENGTH_SHORT).show();
                }

                else {
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();

                    ProjectIdeas projectIdeas = new ProjectIdeas(Utitle,Udescription,today,key);

                    projectDatabase.child(userId).child(key).setValue(projectIdeas).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            progressDialog.dismiss();

                            if(task.isSuccessful())
                            {
                                title.setText("");
                                description.setText("");
                                Toast.makeText(getContext(),"Successfully Added Your Project Idea!!",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                String errMesg = task.getException().getMessage();
                                Toast.makeText(getContext(),errMesg,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }



            }
        });



    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}