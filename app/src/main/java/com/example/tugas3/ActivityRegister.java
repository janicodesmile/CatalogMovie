package com.example.tugas3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityRegister extends AppCompatActivity {

    private TextInputEditText et_email;
    private TextInputEditText et_pw;
    private Button btn_regist,btn_reset;
    private FirebaseAuth mAuth;

    public ActivityRegister() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        et_email =findViewById(R.id.email);
        et_pw = findViewById (R.id.password);
        btn_regist =findViewById(R.id.btn_regis);
        btn_reset = findViewById(R.id.btn_reset);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email.setText("");
                et_pw.setText("");
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String pw = et_pw.getText().toString();
                if(email.equals("") && pw.equals("")){
                    Toast.makeText(ActivityRegister.this,"Email dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if (pw.equals("")){
                    Toast.makeText(ActivityRegister.this,"Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if (email.equals("")){
                    Toast.makeText(ActivityRegister.this,"Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, pw)
                            .addOnCompleteListener(ActivityRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ActivityRegister.this,"Register Berhasil, Silahkan Login",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent2 = new Intent(ActivityRegister.this, LoginActivity.class);
                                        startActivity(intent2);
                                    } else {
                                        Toast.makeText(ActivityRegister.this, "Autentikasi Gagal",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
