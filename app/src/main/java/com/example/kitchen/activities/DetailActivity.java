package com.example.kitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitchen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private DatabaseReference dbref;
    private TextView Judul, Bahan,Langkah, Tag;
    private ImageView Gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Judul = findViewById(R.id.judul);
        Bahan = findViewById(R.id.bahan);
        Langkah = findViewById(R.id.langkah);
        Tag = findViewById(R.id.tag);
        Gambar = findViewById(R.id.gambar);

        dbref = FirebaseDatabase.getInstance().getReference().child("Resep").child("Dinner");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String judul= dataSnapshot.child("judul").getValue().toString();
                Picasso.get().load("gambar").into(Gambar);
                String bahan = dataSnapshot.child("bahan").getValue().toString();
                String langkah = dataSnapshot.child("langkah").getValue().toString();
                String tag = dataSnapshot.child("kunci").getValue().toString();

                Judul.setText(judul);
                Bahan.setText(bahan);
                Langkah.setText(langkah);
                Tag.setText(tag);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
