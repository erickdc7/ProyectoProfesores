package com.example.proyectoprofesores;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class AdapterJustificacion extends RecyclerView.Adapter<AdapterJustificacion.ViewHolderDatos>{

    ArrayList<Justificacion> listDatos;

    public AdapterJustificacion(ArrayList<Justificacion> listDatos) {
        this.listDatos = listDatos;
    }

    private String obtenerColorAleatorio() {
        String[] fondoColor = {"azul", "blanco"};
        Random random = new Random();
        int indiceColor = random.nextInt(fondoColor.length);
        return fondoColor[indiceColor];
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.justificacion_inicio_layout,null,false);
        return new ViewHolderDatos(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        String colorFondo = obtenerColorAleatorio();
        switch (colorFondo){
            case "azul":
                holder.fondo.setBackgroundResource(R.drawable.justificacion_fondo2);
                holder.nombre.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                holder.aula.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                holder.fecha.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                holder.aulaintro.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                holder.fechaintro.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));

                break;
            case "blanco":
                holder.fondo.setBackgroundResource(R.drawable.justificacion_fondo1);
                holder.nombre.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
                holder.aula.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
                holder.fecha.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
                holder.aulaintro.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
                holder.fechaintro.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
                break;

        }
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView aulaintro;
        TextView aula;
        TextView fechaintro;
        TextView fecha;
        ImageView fondo;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.justificacion_nombre);
            aula = itemView.findViewById(R.id.justificacion_aula);
            aulaintro = itemView.findViewById(R.id.aula);
            fechaintro= itemView.findViewById(R.id.espec);
            fecha= itemView.findViewById(R.id.justificacion_especificacion);
            fondo = itemView.findViewById(R.id.fondoJustificacion);
        }

        public void asignarDatos(Justificacion dato) {
            nombre.setText(Justificacion.getApellidos(dato.getNombre()));
            aula.setText(dato.getAula());
            fecha.setText(Justificacion.convertirFechaAString(dato.getFecha()));
        }
    }
}
