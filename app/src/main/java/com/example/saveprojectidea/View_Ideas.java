package com.example.saveprojectidea;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class View_Ideas extends Fragment {



    ProgressDialog progressDialog;
    ArrayList<ProjectIdeas> project_Ideas = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    DatabaseReference projectDatabase;

    RecyclerView  rvProjectList ;

    TextView emptyView;





    public View_Ideas() {
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
        return inflater.inflate(R.layout.fragment_view__ideas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());

        firebaseAuth = FirebaseAuth.getInstance();
        projectDatabase = FirebaseDatabase.getInstance().getReference("Projects");

        emptyView = (TextView)view.findViewById(R.id.textView_Empty);

        rvProjectList = (RecyclerView) view.findViewById(R.id.rvProjectIdeasList);

        rvProjectList.setLayoutManager(new LinearLayoutManager(getContext()));




        //call
        readAllProjectIdeas();



    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void readAllProjectIdeas()
    {

        progressDialog.setMessage("Loading Data ...");
        progressDialog.show();

        //get user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        project_Ideas.clear();;

        projectDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                progressDialog.dismiss();

                for (DataSnapshot snapshot :dataSnapshot.getChildren()) {

                    ProjectIdeas projectIdeasModel = snapshot.getValue(ProjectIdeas.class);

                    project_Ideas.add(projectIdeasModel);
                    Collections.reverse(project_Ideas);

                }

                RecycleAdapter recycleAdapter =  new RecycleAdapter(getContext(),project_Ideas);

                recycleAdapter.notifyDataSetChanged();

                rvProjectList.setHasFixedSize(true);
                rvProjectList.setAdapter(recycleAdapter);

                if(project_Ideas.isEmpty())
                {
                    emptyView.setVisibility(View.VISIBLE);
                    rvProjectList.setVisibility(View.GONE);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }



}