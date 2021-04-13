package com.example.tugas3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Tambah extends AppCompatActivity {

    private EditText tambahjudul, tambahsinopsis, tambahket, tambahtahun, tambahcover;
    private Button btntambah, btntambahcover;
    private ImageView imageView;
    private TextView admin;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri Imageuri;
    private ProgressBar progressBar;
    private StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        admin = findViewById(R.id.emailadmin2);
        tambahjudul = findViewById(R.id.et_tambahjudul);
        tambahsinopsis = findViewById(R.id.et_tambahsinopsis);
        tambahket = findViewById(R.id.et_tambahket);
        tambahtahun = findViewById(R.id.et_tambahtahun);
        btntambah = findViewById(R.id.btn_tambah);
        btntambahcover = findViewById(R.id.btn_tambahcover);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progressBar);

        storageReference = FirebaseStorage.getInstance().getReference("CoverFilm");
        databaseReference = FirebaseDatabase.getInstance().getReference("DataFilm");

        btntambahcover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar();
            }
        });

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            uploadFile();

            }
        });

    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if (Imageuri != null){
            final StorageReference fileRef = storageReference.child(System.currentTimeMillis()+(".")+getFileExtension(Imageuri));
            fileRef.putFile(Imageuri)
                   .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Handler handler = new Handler();
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   progressBar.setProgress(0);
                               }
                           }, 500);

                           Toast.makeText(Tambah.this, "Upload successful", Toast.LENGTH_LONG).show();

                           fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {

                                   HashMap<String,Object> hashMap = new HashMap<>();
                                   hashMap.put("Judul",tambahjudul.getText().toString());
                                   hashMap.put("Sinopsis",tambahsinopsis.getText().toString());
                                   hashMap.put("Keterangan",tambahket.getText().toString());
                                   hashMap.put("Tahun",tambahtahun.getText().toString());
                                   hashMap.put("ImageUrl",String.valueOf(uri));

                                   databaseReference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful()){
                                               Toast.makeText(Tambah.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                                               Intent intent = new Intent(Tambah.this, MainActivity2.class);
                                               intent.putExtra("kirim",admin.getText().toString());
                                               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                               startActivity(intent);
                                           }else {
                                               Toast.makeText(Tambah.this, "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
                                           }

                                       }
                                   });

                               };
                           });
                       }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Tambah.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int)progress);

                        }
                    });
        }else{
            Toast.makeText(Tambah.this, "Tidak ada file yang dipilih", Toast.LENGTH_SHORT).show();
        }
    }

    private void pilihGambar(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Imageuri = data.getData();
            Picasso.get().load(Imageuri).into(imageView);
//            imageView.setImageURI(Imageuri);
        }
    }
}