package com.example.saveprojectidea;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class HomePage extends AppCompatActivity  implements  DBOperation{



    FirebaseAuth firebaseAuth;
    DatabaseReference projectDatabase;

    RecycleAdapter recycleAdapter;

    //View_Ideas view_ideas;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);


        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();
        projectDatabase = FirebaseDatabase.getInstance().getReference("Projects");


         bottomNavigationView = findViewById(R.id.bottem_NavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);



    }

    public void readAllProjectIdeas(Context context, ArrayList<ProjectIdeas> project_Ideas, RecyclerView rvProjectList, TextView emptyView)
    {


        //get user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        project_Ideas.clear();;

        projectDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot :dataSnapshot.getChildren()) {

                    ProjectIdeas projectIdeasModel = snapshot.getValue(ProjectIdeas.class);

                    project_Ideas.add(projectIdeasModel);
                    Collections.reverse(project_Ideas);

                }

                recycleAdapter =  new RecycleAdapter(context,project_Ideas);



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