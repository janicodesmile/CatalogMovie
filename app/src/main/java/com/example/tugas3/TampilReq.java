package com.example.tugas3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TampilReq extends AppCompatActivity {

    private ArrayList<Request> reqlist;
    private RequestCardAdapter reqadapter;
    private RecyclerView recyclerReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_req);

        recyclerReq = findViewById(R.id.recyclerReq);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerReq.setLayoutManager(mLayoutManager);
        recyclerReq.setItemAnimator(new DefaultItemAnimator());

        FirebaseDatabase.getInstance().getReference().child("DataRequestFilm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reqlist = new ArrayList<>();
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Request request = noteDataSnapshot.getValue(Request.class);
                    request.setKey(noteDataSnapshot.getKey());
                    reqlist.add(request);
                }
                reqadapter = new RequestCardAdapter(reqlist,TampilReq.this);
                recyclerReq.setAdapter(reqadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem email = menu.findItem(R.id.email3);
        email.setVisible(false);
        MenuItem list = menu.findItem(R.id.action_list);
        list.setVisible(false);
        MenuItem card = menu.findItem(R.id.action_cardview);
        card.setVisible(false);
        MenuItem req = menu.findItem(R.id.tampilReq);
        req.setVisible(false);
        MenuItem login = menu.findItem(R.id.login);
        login.setVisible(false);
        MenuItem regis = menu.findItem(R.id.regisadmin);
        regis.setVisible(false);
        MenuItem info = menu.findItem(R.id.tampilinfo);
        info.setVisible(false);
        MenuItem setting = menu.findItem(R.id.setting);
        setting.setVisible(false);
        MenuItem logout = menu.findItem(R.id.logout);
        logout.setVisible(false);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                Intent intent = new Intent(TampilReq.this, MainActivity2.class);
                startActivity(intent);
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