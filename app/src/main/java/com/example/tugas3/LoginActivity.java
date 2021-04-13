package com.example.tugas3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emaill, passwordd;
    private Button btn_login;
    private TextView bpa;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emaill = findViewById(R.id.emaill);
        passwordd = findViewById(R.id.passwordd);
        bpa = findViewById(R.id.bpa);
        btn_login = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        Bundle simpan = getIntent().getExtras();
        if(simpan == null){
            emaill.setText("");
        }else{
            String nama = simpan.getString("mail");
            emaill.setText(nama);
        }

        bpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Silahkan Hubungi Admin Untuk Membuat Akun",Toast.LENGTH_SHORT).show();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emaill.getText().toString();
                String pw = passwordd.getText().toString();
                if(email.equals("") && pw.equals("")){
                    Toast.makeText(LoginActivity.this,"Email dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if (pw.equals("")){
                    Toast.makeText(LoginActivity.this,"Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if (email.equals("")){
                    Toast.makeText(LoginActivity.this,"Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, pw)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login Berhasil",
                                                Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                                        intent.putExtra("kirim",email);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Email atau Password Salah",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                };

            });
        }
        };
    });
}
}