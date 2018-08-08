package com.ivonkhalif.ragnarok.kasir_online;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.acl.Owner;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPass;
    private Button btnlogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, PelayanActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.txtemail);
        inputPass = findViewById(R.id.txtpassword);
        btnlogin = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        progressBar = new ProgressDialog(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String email = inputEmail.getText().toString().trim();
                String password = inputPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setMessage("Loading...");
                progressBar.setCancelable(false);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (email.equals("kasir@resto.com")) {
                                startActivity(new Intent(LoginActivity.this, KasirActivity.class));
                            } else if (email.equals("owner@resto.com")) {
                                startActivity(new Intent(LoginActivity.this, OwnerActivity.class));
                            } else if (email.equals("pelayan@resto.com")) {
                                startActivity(new Intent(LoginActivity.this, PelayanActivity.class));
                            } else if (email.equals("koki@resto.com")) {
                                startActivity(new Intent(LoginActivity.this, KokiActivity.class));
                            }

                        } else {
                            Toast.makeText(LoginActivity.this, "gagal login", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.dismiss();

                    }
                });
            }
        });
    }
}
