package com.example.saveprojectidea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ProjectIdeaHandler> {

    Context context;
    ArrayList<ProjectIdeas> projectIdeas;

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

                new DialogCreater(context).displayEditDialog(ideaOfProject);

            }
        });

        holder.im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DialogCreater(context).displayDeleteDialog(ideaOfProject);
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
