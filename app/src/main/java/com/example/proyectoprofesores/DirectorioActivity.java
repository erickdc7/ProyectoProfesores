package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DirectorioActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterDirectorio adapter;
    private ArrayList<Directorio> listDirectorio = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio);


        //recyclerView = findViewById(R.id.recyclerViewDirectorio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //listDirectorio.add(new Directorio(R.drawable.mujer_logo, "Alumna Ariana Fuentes", "553 339 721", "553 397 721"));


        adapter = new AdapterDirectorio(listDirectorio);

        recyclerView.setAdapter(adapter);

    }

}
