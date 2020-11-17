package com.example.saveprojectidea;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class View_Ideas extends Fragment   {



    ProgressDialog progressDialog;
    ArrayList<ProjectIdeas> project_Ideas = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    DatabaseReference projectDatabase;

    RecyclerView  rvProjectList ;

    RecycleAdapter recycleAdapter;

    TextView emptyView;
    ImageView emptyImageView;
    DBOperation dbOperation;


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

        dbOperation = (DBOperation)getContext();

        emptyView = (TextView)view.findViewById(R.id.textView_Empty);
        emptyImageView = (ImageView)view.findViewById(R.id.imageView_NoWifi);


        rvProjectList = (RecyclerView) view.findViewById(R.id.rvProjectIdeasList);

        rvProjectList.setLayoutManager(new LinearLayoutManager(getContext()));


                if((new ChekNetwork().isNetworkAvailable(getContext())))
                {
                    //call
                    dbOperation.readAllProjectIdeas(getContext(),project_Ideas,rvProjectList,emptyView);
                }

                else {

                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setText("No Internet Connection !!");
                    emptyView.setSingleLine();
                    emptyImageView.setVisibility(View.VISIBLE);
                    rvProjectList.setVisibility(View.GONE);
                }

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