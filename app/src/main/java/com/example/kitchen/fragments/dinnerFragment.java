package com.example.kitchen.fragments;


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
import com.example.kitchen.my_adapter.DinnerAdapter;
import com.example.kitchen.my_model.DinnerModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class dinnerFragment extends Fragment {

    private RecyclerView dinnerList;

    private DatabaseReference dinnerReference;

    private List<DinnerModel> dinnerModelList = new ArrayList<>();



    public dinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dinner, container, false);

        dinnerList = view.findViewById(R.id.dinnerRV);
        dinnerReference = FirebaseDatabase.getInstance().getReference();

        dinnerModelList.add(new DinnerModel("Batu","Oseng Batu"));
        dinnerModelList.add(new DinnerModel("Kacang", "Kacang Rebus"));

        dinnerReference.child("Resep").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.getValue().toString();
                if(name.equals("nama")) {
                    DinnerModel dinnerModel = new DinnerModel(name, "test");
                    dinnerModelList.add(dinnerModel);
                }

//                DinnerModel dinnerModel = new DinnerModel(aa, bb);
//
//                dinnerModelList.add(dinnerModel);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Data Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        DinnerAdapter adapter = new DinnerAdapter(dinnerModelList);
        dinnerList.setAdapter(adapter);
        dinnerList.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;

    }

}