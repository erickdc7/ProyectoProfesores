package com.example.proyectoprofesores;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterJustificacion extends RecyclerView.Adapter<AdapterJustificacion.ViewHolderDatos>{

    ArrayList<Justificacion> listDatos;

    public AdapterJustificacion(ArrayList<Justificacion> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.justificacion_inicio_layout,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView aula;
        TextView detalle;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.justificacion_nombre);
            aula = itemView.findViewById(R.id.justificacion_aula);
            detalle= itemView.findViewById(R.id.justificacion_especificacion);
        }

        public void asignarDatos(Justificacion dato) {
            nombre.setText(dato.getNombre());
            aula.setText(dato.getAula());
            detalle.setText(dato.getEspecificacion());
        }
    }
}
