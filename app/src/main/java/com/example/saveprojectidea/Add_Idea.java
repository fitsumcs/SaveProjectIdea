package com.example.saveprojectidea;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Add_Idea extends Fragment {

    EditText title,description;
    String Utitle , Udescription;


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



        Button button = (Button)view.findViewById(R.id.button_addIdea);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utitle = title.getText().toString();
                Udescription = description.getText().toString();
                Toast.makeText(getContext(),Utitle + " : " + Udescription,Toast.LENGTH_LONG).show();

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