package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TampilanInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_info);
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
                    Intent intent = new Intent(TampilanInfo.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(TampilanInfo.this, MainActivity2.class);
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