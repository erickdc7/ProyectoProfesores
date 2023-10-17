package com.example.proyectoprofesores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprofesores.Notificaciones;

import java.util.ArrayList;

public class AdapterNotificaciones extends RecyclerView.Adapter<AdapterNotificaciones.ViewHolderDatos> {

    private ArrayList<Notificaciones> listNotificaciones;

    public AdapterNotificaciones(ArrayList<Notificaciones> listNotificaciones) {
        this.listNotificaciones = listNotificaciones;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_notificaciones, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listNotificaciones.get(position));
    }

    @Override
    public int getItemCount() {
        return listNotificaciones.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imageViewNotificacion;
        TextView textViewTituloNotificacion;
        TextView textViewDescripcion;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imageViewNotificacion = itemView.findViewById(R.id.imageViewNotificacion);
            textViewTituloNotificacion = itemView.findViewById(R.id.textViewTituloNotificacion);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
        }

        public void asignarDatos(Notificaciones notificacion) {
            imageViewNotificacion.setImageResource(notificacion.getImage());
            textViewTituloNotificacion.setText(notificacion.getTitulo());
            textViewDescripcion.setText(notificacion.getNoti());
        }
    }
}