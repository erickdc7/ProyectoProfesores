package com.example.proyectoprofesores;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;


public class CoursesFragment extends Fragment {
    ArrayList<cursodt> listCursos;

    RecyclerView recy;
    RecyclerView recyD;

    String nombre;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            String textoSalon = args .getString("textoSalon", "");
            // Usa textoSalon como desees
        }
        recy = view.findViewById(R.id.recyclercurso);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setHasFixedSize(true);
        recyD = view.findViewById(R.id.recyclerdia);
        listCursos = new ArrayList<>();
        llenarCursos();
        adapterCursos adapter = new adapterCursos(getContext(),listCursos);
        recy.setAdapter(adapter);
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
        listCursos.add(new cursodt(R.drawable.fondo_curso1, R.drawable.logo_math, "Trigonometria", "Secundaria", "3B", "25", listDias));
        ArrayList<String> diasCurso2 = new ArrayList<>();
        diasCurso2.add("Martes");
        listCursos.add(new cursodt(R.drawable.fondo_curso2, R.drawable.logo_math, "Algebra", "Secundaria", "1A", "20", diasCurso2));
        listCursos.add(new cursodt(R.drawable.fondo_curso3, R.drawable.logo_plant, "Biologia", "Secundaria", "5B", "22", listDias));


    }


}