package com.example.kitchen.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kitchen.R;
import com.example.kitchen.my_adapter.DinnerAdapter;
import com.example.kitchen.my_model.DinnerModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class dinnerFragment extends Fragment {

    private View dinnerFood;
    private RecyclerView dinnerList;

    private DatabaseReference dinnerReference;



    public dinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dinnerFood = inflater.inflate(R.layout.fragment_dinner, container, false);

        dinnerList = dinnerFood.findViewById(R.id.dinnerRV);
        dinnerList.setLayoutManager(new LinearLayoutManager(getActivity()));

        dinnerReference = FirebaseDatabase.getInstance().getReference().child("everyonekitchen-78a15").child("Dinner");


        return dinnerFood;

    }

//    FirebaseRecyclerOptions options =
//            new FirebaseRecyclerOptions.Builder<DinnerModel>()
//                    .setQuery(dinnerReference, new SnapshotParser<DinnerModel>() {
//                        @NonNull
//                        @Override
//                        public DinnerModel parseSnapshot(@NonNull DataSnapshot snapshot) {
//                            return new DinnerModel(snapshot.child("title").getValue().toString(),
//                                    snapshot.child("deskripsi").getValue().toString());
//                        }
//                    })
//                    .build();
//
//    FirebaseRecyclerAdapter<DinnerModel, DinnerViewHolder> adapter
//            = new FirebaseRecyclerAdapter<DinnerModel, DinnerViewHolder>(options) {
//        @Override
//        protected void onBindViewHolder(@NonNull DinnerViewHolder holder, int position, @NonNull DinnerModel model) {
//            holder.ttl.setText("title");
//            holder.desc.setText("deskripsi");
//        }
//
//        @NonNull
//        @Override
//        public DinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_menu, parent, false);
//            return new DinnerViewHolder(view);
//        }
//    };
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }

        @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DinnerModel> options =
                new FirebaseRecyclerOptions.Builder<DinnerModel>()
                .setQuery(dinnerReference, DinnerModel.class)
                .build();



        final FirebaseRecyclerAdapter<DinnerModel, DinnerViewHolder> adapter
                = new FirebaseRecyclerAdapter<DinnerModel, DinnerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final DinnerViewHolder holder, int position, @NonNull DinnerModel model) {

                String getDinner = getRef(position).getKey();

                dinnerReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild("deskripsi")) {
                            String judul = dataSnapshot.child("title").getValue().toString();
                            String deskrip = dataSnapshot.child("deskripsi").getValue().toString();

                            holder.ttl.setText(judul);
                            holder.desc.setText(deskrip);
                        }

                        else {
                            holder.ttl.setText("Tidak Ditemukan");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public DinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_menu, parent, false);
                DinnerViewHolder dinnerViewHolder = new DinnerViewHolder(view);
                return dinnerViewHolder;
            }
        };

        dinnerList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class DinnerViewHolder extends RecyclerView.ViewHolder {

        TextView ttl, desc;

        public DinnerViewHolder(@NonNull View itemView) {
            super(itemView);

            ttl = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.deskripsi);


        }

    }
}