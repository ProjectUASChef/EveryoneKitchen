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

import com.example.kitchen.R;
import com.example.kitchen.my_model.LunchModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class lunchFragment extends Fragment {


    private View LunchView;
    private RecyclerView myLunchList;
    private DatabaseReference LunchRef, UserRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public lunchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LunchView = inflater.inflate(R.layout.fragment_lunch, container, false);

        myLunchList = LunchView.findViewById(R.id.lunchRV);
        myLunchList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        LunchRef = FirebaseDatabase.getInstance().getReference().child("Dinner");


        return LunchView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<LunchModel> options =
                new FirebaseRecyclerOptions.Builder<LunchModel>()
                .setQuery(LunchRef, LunchModel.class)
                .build();

        FirebaseRecyclerAdapter<LunchModel, LunchViewHolder> adapter =
                new FirebaseRecyclerAdapter<LunchModel, LunchViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final LunchViewHolder holder, int position, @NonNull LunchModel model) {

                        String userID = getRef(position).getKey();

                        LunchRef.child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("deskripsi")) {
                                    String dess = dataSnapshot.child("deskripsi").getValue().toString();

                                    holder.ttl.setText(dess);
                                }
                                else {
                                    holder.ttl.setText("Data Tidak Ditemukan");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public LunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_menu, parent, false);
                        return new LunchViewHolder(view);
                    }
                };

        myLunchList.setAdapter(adapter);
        adapter.startListening();

    }

    public static class LunchViewHolder extends RecyclerView.ViewHolder {

        TextView ttl, desc;

        public LunchViewHolder(@NonNull View itemView) {
            super(itemView);

            ttl = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.deskripsi);
        }
    }
}
