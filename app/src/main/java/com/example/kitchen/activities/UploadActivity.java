package com.example.kitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kitchen.R;
import com.example.kitchen.my_model.MyModel;
import com.example.kitchen.my_model.UploadModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    private Button btupload;
    private ImageView gambar;
    private EditText judul, bahan, langkah;
    private DatabaseReference dbref;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        judul = findViewById(R.id.judulresep);
        bahan = findViewById(R.id.bahantext);
        langkah = findViewById(R.id.langkah);
        btupload = findViewById(R.id.addresep);
        gambar = findViewById(R.id.gambar);
        spinner = findViewById(R.id.spinner);

        dbref = FirebaseDatabase.getInstance().getReference();
        btupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String judulresep = judul.getText().toString().trim();
//                final String gambarresep = gambar.
                final String bahanresep = bahan.getText().toString().trim();
                final String langkahresep = langkah.getText().toString().trim();
                final String lock = spinner.getSelectedItem().toString();

                if (judulresep.isEmpty()){
                    judul.setError("Masukkan Judul Resep");
                }
                else if (bahanresep.isEmpty()){
                    bahan.setError("Masukkan Bahan-bahan resep Anda");
                }
                else if (langkahresep.isEmpty()){
                    langkah.setError("Masukkan Langkah-langkah Pembuatan Anda !");
                }
                else{
                    addData(new UploadModel(judulresep, langkahresep, bahanresep, lock));
                }
            }

        });
    }
    public void addData(final UploadModel upload){
        dbref.child("Resep").child(upload.getKunci()).push().setValue(upload).addOnSuccessListener(UploadActivity.this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UploadActivity.this, "Resep Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void PilihGambar(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.gambar);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
