package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCurso extends RecyclerView.Adapter<AdapterCurso.ViewHolderDatos>{

    ArrayList<CursoInicio> listCursos;
    Context context;

    public AdapterCurso(ArrayList<CursoInicio> listCursos, Context context) {
        this.listCursos= listCursos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.curso_inicio_layout,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listCursos.get(position).curso);
    }

    @Override
    public int getItemCount() {
        return listCursos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dato;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.idCurso);
        }

        public void asignarDatos(String datos) {
            dato.setText(datos);
        }
    }
}
