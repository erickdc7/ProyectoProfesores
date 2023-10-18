package com.example.proyectoprofesores;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificacionesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterNotificaciones adapter;
    private ArrayList<Notificaciones> listaNotificaciones = new ArrayList<>();
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        back = findViewById(R.id.retro);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerViewNotificaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaNotificaciones.add(new Notificaciones(R.drawable.icon_branded_frame_32, "Reunión de profesores", "Reunión de profesores programada para el próximo viernes a las 10:00 AM en la sala de conferencias."));


        adapter = new AdapterNotificaciones(listaNotificaciones);

        recyclerView.setAdapter(adapter);
    }
}
