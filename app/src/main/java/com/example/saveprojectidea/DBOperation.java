package com.example.saveprojectidea;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public interface DBOperation {
    public void readAllProjectIdeas(Context context, ArrayList<ProjectIdeas> project_Ideas, RecyclerView rvProjectList, TextView emptyView);
}
