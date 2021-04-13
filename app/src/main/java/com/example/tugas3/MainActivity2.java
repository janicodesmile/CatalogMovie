package com.example.tugas3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity{

    private DatabaseReference database;

    private ArrayList<Film> filmArrayList;
    private RecyclerViewAdapter adapter;
    private CardViewAdapter adapter2;

    private RecyclerView recyclerView;

    private FloatingActionButton btnTambah;
    private TextView nama_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nama_email = findViewById(R.id.emailadmin);

        btnTambah =findViewById(R.id.buttontambahdata);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Tambah.class);
                intent.putExtra("kirim",nama_email.getText().toString());
                startActivity(intent);
            }
        });


        database = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        database.child("DataFilm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                filmArrayList = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Film data= noteDataSnapshot.getValue(Film.class);
                    data.setKey(noteDataSnapshot.getKey());
                    filmArrayList.add(data);
                }
                adapter = new RecyclerViewAdapter(filmArrayList, MainActivity2.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem emailll = menu.findItem(R.id.email3);
        Bundle simpan = getIntent().getExtras();
        String nama = simpan.getString("kirim");
        emailll.setTitle(nama);

        MenuItem guest = menu.findItem(R.id.logout);
        guest.setTitle("Logout");
        nama_email.setText(nama);

        MenuItem login = menu.findItem(R.id.login);
        login.setVisible(false);

        MenuItem back = menu.findItem(R.id.back);
        back.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                Toast.makeText(this,getString(R.string.onclick1) , Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(adapter);
                return true;
            case R.id.action_cardview:
                Toast.makeText(this,getString(R.string.onclick2), Toast.LENGTH_SHORT).show();
                adapter2 = new CardViewAdapter(filmArrayList, MainActivity2.this);
                recyclerView.setAdapter(adapter2);
                return true;
            case R.id.setting:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                return true;
            case R.id.tampilinfo:
                Intent intent2 = new Intent(MainActivity2.this, TampilanInfo.class);
                startActivity(intent2);
                return true;
            case R.id.logout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder .setTitle("Konfirmasi Logout")
                            .setMessage("Apakah anda yakin ingin logout?")
                            .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id ){
                                    // CONFIRM
                                    Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    String nama = nama_email.getText().toString();
                                    intent.putExtra("mail",nama);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // CANCEL
                                }
                            }).show();
                    return  true;
            case R.id.regisadmin:
                Intent intent3 = new Intent(MainActivity2.this, ActivityRegister.class);
                startActivity(intent3);
                return true;
            case R.id.tampilReq:
                Intent intent4 = new Intent(MainActivity2.this, TampilReq.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem emailll = menu.findItem(R.id.email3);
        Bundle simpan = getIntent().getExtras();
        String nama = simpan.getString("kirim");
        emailll.setTitle(nama);
        return super.onCreateOptionsMenu(menu);
    }



}

