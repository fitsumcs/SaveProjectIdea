package com.example.saveprojectidea;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ProjectIdeaHandler> {

    Context context;
    ArrayList<ProjectIdeas> projectIdeas;

    public RecycleAdapter(Context context, ArrayList<ProjectIdeas> projectIdeas)
    {

        this.context = context;
        this.projectIdeas = projectIdeas;


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

                displayEditDialog();

            }
        });
    }

    private void displayEditDialog() {

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog_layout);
        dialog.show();





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
