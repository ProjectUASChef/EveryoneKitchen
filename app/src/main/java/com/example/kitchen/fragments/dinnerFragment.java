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
import android.widget.Toast;

import com.example.kitchen.R;
import com.example.kitchen.UploadActivity;
import com.example.kitchen.my_adapter.MyAdapter;
import com.example.kitchen.my_model.MyModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
public class dinnerFragment extends Fragment {
    FloatingActionButton createResep;
    private RecyclerView dinnerList;

    private DatabaseReference dinnerReference;

    private List<MyModel> myModelList = new ArrayList<>();



    public dinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_dinner, container, false);
        createResep = view.findViewById(R.id.floatingActionButton);
        dinnerList = view.findViewById(R.id.dinnerRV);
        dinnerReference = FirebaseDatabase.getInstance().getReference();

        myModelList.add(new MyModel("Batu","Oseng Batu"));
        myModelList.add(new MyModel("Kacang", "Kacang Rebus"));

        dinnerReference.child("Resep").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.getValue().toString();
                if(name.equals("nama")) {
                    MyModel myModel = new MyModel(name, "test");
                    myModelList.add(myModel);
                }

//                MyModel dinnerModel = new MyModel(aa, bb);
//
//                myModelList.add(dinnerModel);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Data Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        MyAdapter adapter = new MyAdapter(myModelList);
        dinnerList.setAdapter(adapter);
        dinnerList.setLayoutManager(new LinearLayoutManager(getContext()));

        createResep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),UploadActivity.class));
            }
        });

        return view;

    }



}