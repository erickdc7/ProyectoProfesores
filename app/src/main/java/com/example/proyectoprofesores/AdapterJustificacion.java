package com.example.proyectoprofesores;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class AdapterJustificacion extends RecyclerView.Adapter<AdapterJustificacion.ViewHolderDatos>{

    ArrayList<Justificacion> listDatos;

    public AdapterJustificacion(ArrayList<Justificacion> listDatos) {
        this.listDatos = listDatos;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.justificacion_inicio_layout,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        String colorFondo = obtenerColorAleatorio();
        switch (colorFondo){
            case "azul":
                holder.fondo.setBackgroundResource(R.drawable.justificacion_inicio_azul);

                break;
            case "marron":
                holder.fondo.setBackgroundResource(R.drawable.justificacion_inicio_marron);
                break;
            case "rojo":
                holder.fondo.setBackgroundResource(R.drawable.justificacion_inicio_rojo);
                break;
            case "verde":
                holder.fondo.setBackgroundResource(R.drawable.justificacion_inicio_verde);
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
        TextView aula;
        TextView fecha;
        ImageView fondo;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.justificacion_nombre);
            aula = itemView.findViewById(R.id.justificacion_aula);
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
