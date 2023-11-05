package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JustiFalAdapter extends RecyclerView.Adapter<JustiFalAdapter.ViewHolderDatos> {
    ArrayList<JustiFaltas> listaFaltas;
    Context context;

    public JustiFalAdapter(ArrayList<JustiFaltas> listaFaltas, Context context) {
        this.listaFaltas = listaFaltas;
        this.context = context;
    }

    @NonNull
    @Override
    public JustiFalAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listview_justificaciones, parent, false);
        return  new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JustiFalAdapter.ViewHolderDatos holder, int position) {
        holder.nombre.setText(listaFaltas.get(position).nomAlumno);
        holder.aula.setText(listaFaltas.get(position).aula);
        holder.anotacion.setText(listaFaltas.get(position).descripcion);
    }

    @Override
    public int getItemCount() {
        return listaFaltas.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView nombre, aula, anotacion;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.textView9);
            aula = itemView.findViewById(R.id.textView12);
            anotacion = itemView.findViewById(R.id.textView10);
        }
    }
}
