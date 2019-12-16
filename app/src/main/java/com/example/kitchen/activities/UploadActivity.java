package com.example.kitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kitchen.R;
import com.example.kitchen.my_model.MyModel;
import com.example.kitchen.my_model.UploadModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

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
    private StorageReference mstorageRef;
    private Uri mImageUri;

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
        mstorageRef = FirebaseStorage.getInstance().getReference("Resep");

        dbref = FirebaseDatabase.getInstance().getReference();
        btupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
//                final String judulresep = judul.getText().toString().trim();
//                final String bahanresep = bahan.getText().toString().trim();
//                final String langkahresep = langkah.getText().toString().trim();
//                final String lock = spinner.getSelectedItem().toString();
//
//
//
//
//                if (judulresep.isEmpty()){
//                    judul.setError("Masukkan Judul Resep");
//                }
//                else if (bahanresep.isEmpty()){
//                    bahan.setError("Masukkan Bahan-bahan resep Anda");
//                }
//
//                else if (langkahresep.isEmpty()){
//                    langkah.setError("Masukkan Langkah-langkah Pembuatan Anda !");
//                }
//                else{
//                    addData(new UploadModel(judulresep, langkahresep, bahanresep, lock));
//                }
//
            }
//
        });
        }

//        StorageReference filereference = mstorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
//        filereference.putFile(mImageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        dbref.child("Resep").child(upload.getKunci()).push().setValue(upload);
//                        Toast.makeText((UploadActivity.this, "Upload Successful",Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(UploadActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

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

            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(gambar);

//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                // Log.d(TAG, String.valueOf(bitmap));
//
//                ImageView imageView = (ImageView) findViewById(R.id.gambar);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void addData(){
        if (judul == null){
            Toast.makeText(this, "Isikan Judul Resep",Toast.LENGTH_SHORT).show();
        }
        else if (bahan == null){
            Toast.makeText(this, "Isikan Bahan Resep",Toast.LENGTH_SHORT).show();
        }
        else if (langkah == null){
            Toast.makeText(this, "Isikan Langkah Pembuatan Resep",Toast.LENGTH_SHORT).show();
        }
        else if (mImageUri == null){
            Toast.makeText(this, "Sisipkan Gambar Resep",Toast.LENGTH_SHORT).show();
        }
        else{
//            addData(new UploadModel(judulresep, langkahresep, bahanresep, lock));

            final StorageReference filereference = mstorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            filereference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filereference.putFile(mImageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(UploadActivity.this, "Upload Succes",Toast.LENGTH_SHORT).show();
                                    UploadModel upload = new UploadModel(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(),
                                            judul.getText().toString().trim(),
                                            langkah.getText().toString().trim(),
                                            bahan.getText().toString().trim(),
                                            spinner.getSelectedItem().toString()
                                    );
                                    String UploadId = dbref.push().getKey();
                                    dbref.child(UploadId).setValue(upload);
                                    dbref.child("Resep").child(upload.getKunci()).push().setValue(upload);
                                }
                            });
                }
            });
        }
    }
}
