package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ArrayList<Film> filmArrayList;
    private RecyclerViewAdapter adapter;
    private CardViewAdapter adapter2;

    private RecyclerView recyclerView;
    private TextView dddd;
    private FloatingActionButton btn_req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_req = findViewById(R.id.btn_req);
        dddd = findViewById(R.id.dddd);
        recyclerView = findViewById(R.id.recycler);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FirebaseDatabase.getInstance().getReference().child("DataFilm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                filmArrayList = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Film data= noteDataSnapshot.getValue(Film.class);
                    data.setKey(noteDataSnapshot.getKey());
                    filmArrayList.add(data);
                }
                adapter = new RecyclerViewAdapter(filmArrayList, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahRequest.class);
                startActivity(intent);
            }
        });


    }

    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem regis = menu.findItem(R.id.regisadmin);
        regis.setVisible(false);
        MenuItem req = menu.findItem(R.id.tampilReq);
        req.setVisible(false);
        MenuItem back = menu.findItem(R.id.back);
        back.setVisible(false);

        Bundle simpan = getIntent().getExtras();
        String nama = simpan.getString("kirim");
        MenuItem emailll = menu.findItem(R.id.email3);
        emailll.setTitle(nama);

        MenuItem guest = menu.findItem(R.id.logout);
        guest.setTitle("Keluar");

        dddd.setText(nama);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                Toast.makeText(this,"Tampilan Mode List" , Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(adapter);
                return true;
            case R.id.action_cardview:
                Toast.makeText(this,getString(R.string.onclick2), Toast.LENGTH_SHORT).show();
                adapter2 = new CardViewAdapter(filmArrayList,MainActivity.this);
                recyclerView.setAdapter(adapter2);
                return true;
            case R.id.setting:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                return true;
            case R.id.tampilinfo:
                Intent intent2 = new Intent(MainActivity.this, TampilanInfo.class);
                intent2.putExtra("kirim",dddd.getText().toString());
                startActivity(intent2);
                return true;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Keluar");
                builder.setMessage("Apakah kamu yakin ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish
                        MainActivity.this.finishAffinity();
                        System.exit(0);

                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss dialog
                        dialog.dismiss();
                    }
                });
                //show dialog
                builder.show();
                return true;
            case R.id.login:
                Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}

