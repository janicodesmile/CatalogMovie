package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HalamanDetail extends AppCompatActivity {

    private String id,judul,sinopsis,keterangan,tahun,imageUrl;
    private ImageView detailGambar;
    private TextView detailJudul, detailSinopsis,detailTahun,detailKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail);

        detailGambar = findViewById(R.id.iv_detailgambar);
        detailJudul = findViewById(R.id.tv_detailjudul);
        detailTahun = findViewById(R.id.tv_detailtahun);
        detailKeterangan = findViewById(R.id.tv_detailketerangan);
        detailSinopsis = findViewById(R.id.tv_detailsinopsis);

        id = getIntent().getStringExtra("id");
        judul = getIntent().getStringExtra("judul");
        sinopsis = getIntent().getStringExtra("sinopsis");
        keterangan = getIntent().getStringExtra("keterangan");
        tahun = getIntent().getStringExtra("tahun");
        imageUrl = getIntent().getStringExtra("imageUrl");


        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.img)
                .into(detailGambar);
        detailJudul.setText(judul);
        detailTahun.setText(tahun);
        detailKeterangan.setText(keterangan);
        detailSinopsis.setText(sinopsis);
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
                Bundle simpan = getIntent().getExtras();
                String nama = simpan.getString("kirim");

                if(nama.equals("Guest")){
                    Intent intent = new Intent(HalamanDetail.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(HalamanDetail.this, MainActivity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }


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