package com.example.proyectoprofesores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {
    ImageView back;
    Button actualizar;
    String idUsuario;
    String idDocente;
    String nombre;
    String apellido;
    String correo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent intent = getIntent();
        if (intent != null) {
            idUsuario= intent.getStringExtra("idUsuario");
            idDocente= intent.getStringExtra("idDocente");
            nombre= intent.getStringExtra("nombre");
            apellido= intent.getStringExtra("apellido");
            correo= intent.getStringExtra("correo");
        }
        back = findViewById(R.id.backB);
        actualizar =findViewById(R.id.button_actualilzar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.completar_espacio, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
