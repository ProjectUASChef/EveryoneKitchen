package com.example.kitchen.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitchen.R;
import com.example.kitchen.activities.UploadActivity;
import com.example.kitchen.my_adapter.MyAdapter;
import com.example.kitchen.my_model.LunchModel;
import com.example.kitchen.my_model.MyModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class lunchFragment extends Fragment {


    FloatingActionButton createResep;
    private RecyclerView LunchList;
    private DatabaseReference LunchReference;
    private List<MyModel> myModelList = new ArrayList<>();
    private MyAdapter adapter;

    public lunchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lunch, container, false);
        createResep = view.findViewById(R.id.floatingActionButton);
        LunchList = view.findViewById(R.id.lunchRV);
        LunchReference = FirebaseDatabase.getInstance().getReference();

        LunchReference.child("Resep").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MyModel myModel = postSnapshot.getValue(MyModel.class);
                    myModelList.add(myModel);
                }
                adapter = new MyAdapter(myModelList);
                LunchList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Data Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        MyAdapter adapter = new MyAdapter(myModelList);
        LunchList.setAdapter(adapter);
        LunchList.setLayoutManager(new LinearLayoutManager(getContext()));

        createResep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UploadActivity.class));
            }
        });

        return view;
    }
}
