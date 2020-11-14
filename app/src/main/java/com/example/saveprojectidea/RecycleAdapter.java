package com.example.saveprojectidea;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ProjectIdeaHandler> {

    Context context;
    ArrayList<ProjectIdeas> projectIdeas;

    String projectId  , newdate;
    String userId;
    FirebaseUser firebaseUser;

    DatabaseReference projectDatabase;
    FirebaseAuth firebaseAuth;

    public RecycleAdapter(Context con, ArrayList<ProjectIdeas> projectIdeas)
    {

        this.context = con;
        this.projectIdeas = projectIdeas;
        projectDatabase = FirebaseDatabase.getInstance().getReference("Projects");
        firebaseAuth = FirebaseAuth.getInstance();
        //get user
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();




    }

    @NonNull
    @Override
    public ProjectIdeaHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        ProjectIdeaHandler projectIdeaHandler = new ProjectIdeaHandler(view);
        return projectIdeaHandler;

    }

    @Override
    public void onBindViewHolder(@NonNull ProjectIdeaHandler holder, int position) {

        ProjectIdeas ideaOfProject = projectIdeas.get(position);
        String title = ideaOfProject.getProjectTitle();
        String description = ideaOfProject.getProjectDescription();
        String regestiredDate = ideaOfProject.getProjectDate();

        //set the view
        holder.tv_title.setText(title);
        holder.tv_description.setText(description);
        holder.tv_date.setText(regestiredDate);

        //edit image
        holder.im_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayEditDialog(ideaOfProject);

            }
        });

        holder.im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDeleteDialog(ideaOfProject);
            }
        });
    }

    private void displayDeleteDialog(ProjectIdeas projectIdeasItem) {
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

    private void displayEditDialog(ProjectIdeas projectIdeasItem) {



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

    @Override
    public int getItemCount() {
        return projectIdeas.size();
    }

    public class ProjectIdeaHandler extends RecyclerView.ViewHolder{

        TextView tv_title, tv_description, tv_date;
        ImageView im_edit,im_delete;

        public ProjectIdeaHandler(@NonNull View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.textView_Title);
            tv_description = (TextView) itemView.findViewById(R.id.textView_Description);
            tv_date = (TextView) itemView.findViewById(R.id.textView_Date);

            im_edit = (ImageView) itemView.findViewById(R.id.image_edit);
            im_delete = (ImageView) itemView.findViewById(R.id.image_delete);


        }
    }


}
