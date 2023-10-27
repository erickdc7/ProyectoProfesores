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

        listaNotificaciones.add(new Notificaciones(R.drawable.icon_branded_frame_32,
                "Add Templates",
                "To start work with data, please create your first components templates."));
        listaNotificaciones.add(new Notificaciones(R.drawable.emoji,
                "Almost done!",
                "Complete registration of your business profile to start work."));
        listaNotificaciones.add(new Notificaciones(0,
                "Missing things...",
                "To start work with data, please connect your first components templates."));
        listaNotificaciones.add(new Notificaciones(R.drawable.icon_branded_frame_32,
                "Add Templates",
                "To start work with data, please create your first components templates."));
        listaNotificaciones.add(new Notificaciones(R.drawable.emoji,
                "Almost done!",
                "Complete registration of your business profile to start work."));
        listaNotificaciones.add(new Notificaciones(0,
                "Missing things...",
                "To start work with data, please connect your first components templates."));
        listaNotificaciones.add(new Notificaciones(R.drawable.icon_branded_frame_32,
                "Add Templates",
                "To start work with data, please create your first components templates."));
        listaNotificaciones.add(new Notificaciones(R.drawable.emoji,
                "Almost done!",
                "Complete registration of your business profile to start work."));
        listaNotificaciones.add(new Notificaciones(0,
                "Missing things...",
                "To start work with data, please connect your first components templates."));


        adapter = new AdapterNotificaciones(listaNotificaciones);

        recyclerView.setAdapter(adapter);
    }
}
