package com.example.proyectoprofesores;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class AdapterCurso extends RecyclerView.Adapter<AdapterCurso.ViewHolderDatos>{

    ArrayList<CursoInicio> listCursos;
    Context context;

    public AdapterCurso(ArrayList<CursoInicio> listCursos, Context context) {
        this.listCursos= listCursos;
        this.context = context;
    }

    private String obtenerColorAleatorio() {
        String[] fondoColor = {"azul", "marron", "rojo", "verde"};
        Random random = new Random();
        int indiceColor = random.nextInt(fondoColor.length);
        return fondoColor[indiceColor];
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.curso_inicio_layout,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        String colorFondo = obtenerColorAleatorio();
        switch (colorFondo){
            case "azul":
                holder.fondo.setBackgroundResource(R.drawable.curso_inicio_azul);

                break;
            case "marron":
                holder.fondo.setBackgroundResource(R.drawable.curso_inicio_marron);
                break;
            case "rojo":
                holder.fondo.setBackgroundResource(R.drawable.curso_inicio_rojo);
                break;
            case "verde":
                holder.fondo.setBackgroundResource(R.drawable.curso_inicio_verde);
                break;
        }

        holder.asignarDatos(listCursos.get(position).curso);

    }

    @Override
    public int getItemCount() {
        return listCursos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dato;
        RelativeLayout fondo;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.idCurso);
            fondo = itemView.findViewById(R.id.linear_course);
        }

        public void asignarDatos(String datos) {
            dato.setText(datos);
        }
    }
}
