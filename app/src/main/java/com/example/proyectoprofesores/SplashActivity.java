package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    String idUsuario;
    String idDocente;
    String nombre;
    String apellido;
    String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = getIntent();
        if (intent != null) {
            idUsuario= intent.getStringExtra("idUsuario");
            idDocente= intent.getStringExtra("idDocente");
            nombre= intent.getStringExtra("nombre");
            apellido= intent.getStringExtra("apellido");
            correo= intent.getStringExtra("correo");

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("idUsuario", idUsuario);
                intent.putExtra("idDocente", idDocente);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido", apellido);
                intent.putExtra("correo", correo);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}