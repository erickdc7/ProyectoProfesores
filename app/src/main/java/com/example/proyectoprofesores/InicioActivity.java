package com.example.proyectoprofesores;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity{
    ArrayList<String> listaCursos;
    ArrayList<Justificacion> listaJustificaciones;
    RecyclerView recyclerCurso;
    RecyclerView recyclerJustificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        recyclerCurso = (RecyclerView) findViewById(R.id.recyclerCourseId);
        recyclerCurso.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        listaCursos = new ArrayList<String>();
        for(int i = 0; i<=50; i++){
            listaCursos.add("Curso #" + i);
        }
        AdapterCurso adapterc = new AdapterCurso(listaCursos);
        recyclerCurso.setAdapter(adapterc);



        recyclerJustificacion = (RecyclerView) findViewById(R.id.recyclerJustiId);
        recyclerJustificacion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        listaJustificaciones = new ArrayList<Justificacion>();
        for(int i = 0; i <=50; i++){
            listaJustificaciones.add(new Justificacion("nombre #" + i, "aula #" + i, "detalle #" + i));
        }
        AdapterJustificacion adapterj = new AdapterJustificacion(listaJustificaciones);
        recyclerJustificacion.setAdapter(adapterj);



    }
}
