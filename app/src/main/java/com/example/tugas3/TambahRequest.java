package com.example.tugas3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class TambahRequest extends AppCompatActivity {

    private EditText et_reqnama, et_reqjudul;
    private Button btn_req;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_request);

        et_reqjudul = findViewById(R.id.et_reqjudul);
        et_reqnama = findViewById(R.id.et_reqnama);
        btn_req = findViewById(R.id.btn_reqq);
        databaseReference = FirebaseDatabase.getInstance().getReference("DataRequestFilm");

        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String judul = et_reqjudul.getText().toString();
                final String nama = et_reqnama.getText().toString();

                if (judul.equals("") && nama.equals("")){
                    Toast.makeText(TambahRequest.this,"Nama dan Judul Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else if(judul.equals("")){
                    Toast.makeText(TambahRequest.this,"Judul Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else if(nama.equals("")){
                    Toast.makeText(TambahRequest.this,"Nama Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else {
                    uploadRequest();
                }
            }
        });
    }

    public void uploadRequest(){

        Date d = new Date();
        CharSequence s  = DateFormat.format("d MMMM yyyy ", d.getTime());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("Nama",et_reqnama.getText().toString());
        hashMap.put("Judul",et_reqjudul.getText().toString());
        hashMap.put("Date",s.toString());
        hashMap.put("Time",currentTime);

        databaseReference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(TambahRequest.this, "Permintaan Anda Telah Dikirim", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahRequest.this, MainActivity.class);
                    intent.putExtra("kirim", "Guest");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(TambahRequest.this, "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}