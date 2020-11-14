package com.example.saveprojectidea;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogCreater {


    Context context;
    String userId;
    FirebaseUser firebaseUser;
    String projectId  , newdate;

    DatabaseReference projectDatabase;
    FirebaseAuth firebaseAuth;

    public  DialogCreater(Context context1)
    {

        context = context1;
        projectDatabase = FirebaseDatabase.getInstance().getReference("Projects");
        firebaseAuth = FirebaseAuth.getInstance();
        //get user
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();

    }


    public void displayDeleteDialog(ProjectIdeas projectIdeasItem) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_dialog_layout);
        dialog.show();

        //view
        TextView noView = (TextView) dialog.findViewById(R.id.textView_No);
        TextView yesView = (TextView) dialog.findViewById(R.id.textView_Yes);

        noView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        yesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                projectDatabase.child(userId).child(projectIdeasItem.getProjectId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(context,"Successfully Deleted!!!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                dialog.dismiss();
            }
        });
    }

    public void displayEditDialog(ProjectIdeas projectIdeasItem) {



        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog_layout);
        dialog.show();

        EditText editTitle = (EditText)dialog.findViewById(R.id.editTextUpdateTitle);
        EditText editDescription = (EditText)dialog.findViewById(R.id.editTextUpdateDescription);

        editTitle.setText(projectIdeasItem.getProjectTitle());
        editDescription.setText(projectIdeasItem.getProjectDescription());

        projectId = projectIdeasItem.getProjectId();

        Button updateButton = (Button) dialog.findViewById(R.id.button_updateIdea);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newdate = new DateFormater().getFormatedDate();


                //new Data
                if(TextUtils.isEmpty(editTitle.getText()) || TextUtils.isEmpty((editDescription.getText())))
                {
                    Toast.makeText(context,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                //get new data
                String updatedTitle = editTitle.getText().toString();
                String updatedDesc = editDescription.getText().toString();

                ProjectIdeas updatedIdea = new ProjectIdeas(updatedTitle,updatedDesc,newdate,projectId);

                projectDatabase.child(userId).child(updatedIdea.getProjectId()).setValue(updatedIdea).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(context,"Successfully Updated!!!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                dialog.dismiss();




            }
        });



    }
}
