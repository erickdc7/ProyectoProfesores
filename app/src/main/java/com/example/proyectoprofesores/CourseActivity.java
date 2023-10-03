package com.example.proyectoprofesores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    ArrayList<cursodt> listCursos;

    RecyclerView recy;
    RecyclerView recyD;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cursos);

        recy = findViewById(R.id.recyclercurso);
        recy.setLayoutManager(new LinearLayoutManager(this));

        recyD = findViewById(R.id.recyclerdia);
        listCursos = new ArrayList<>();

        llenarCursos();
        adapterCursos adapter = new adapterCursos(listCursos);
        recy.setAdapter(adapter);
        // Obtiene una referencia al ViewHolderDatos
        adapterCursos.ViewHolderDatos viewHolder = (adapterCursos.ViewHolderDatos) recy.findViewHolderForAdapterPosition(0);

// Configura el RecyclerView de días
        if (viewHolder != null) {
            viewHolder.setDias(listCursos.get(0).getDias());
        }






    }

    private void llenarCursos() {
        ArrayList<String> listDias= new ArrayList<>();
        listDias.add("Lunes");
        listDias.add("Martes");
        listCursos.add(new cursodt(R.drawable.fondo_curso1, R.drawable.logo_math, "Trigonometria", "Secundaria", "3ero", "B", "25", listDias));
        ArrayList<String> diasCurso2 = new ArrayList<>();
        diasCurso2.add("Martes");
        listCursos.add(new cursodt(R.drawable.fondo_curso2, R.drawable.logo_math, "Algebra", "Secundaria", "1ro", "A", "20", diasCurso2));
        listCursos.add(new cursodt(R.drawable.fondo_curso3, R.drawable.logo_plant, "Biologia", "Secundaria", "5to", "B", "22", listDias));


    }
}
