package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterCursos extends RecyclerView.Adapter<adapterCursos.ViewHolderDatos> {
    Context context;
    ArrayList<cursodt> listCursos;
    OnCursoClickListener listener;

    public void setOnCursoClickListener(OnCursoClickListener listener){
        this.listener = listener;
    }

    public adapterCursos(Context context, ArrayList<cursodt> listCursos) {
        this.context = context;
        this.listCursos = listCursos;
    }

    public adapterCursos(Context context, ArrayList<cursodt> listCursos, OnCursoClickListener listener) {        this.context = context;
        this.listCursos = listCursos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public adapterCursos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_curso, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterCursos.ViewHolderDatos holder, int position) {
        holder.nombre.setText(listCursos.get(position).getNombre());
        holder.nivel.setText(listCursos.get(position).getNivel());
        holder.aula.setText(listCursos.get(position).getAula());
        holder.cantAlum.setText(listCursos.get(position).getCantAlum());
        holder.fondo.setImageResource(listCursos.get(position).getFondo());
        holder.icon.setImageResource(listCursos.get(position).getIcon());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        holder.recyDias.setLayoutManager(layoutManager);
        ArrayList<String> dias = listCursos.get(position).getDias();
        adapterDias adapterDias = new adapterDias(dias);
        holder.recyDias.setAdapter(adapterDias);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                listener.onCursoClick(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCursos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre, nivel, aula, sesion, cantAlum;
        ImageView fondo, icon;
        RecyclerView recyDias;
        adapterDias adapterDias;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.titleCurso);
            nivel = itemView.findViewById(R.id.nivelCurso);
            cantAlum = itemView.findViewById(R.id.cantAlumCurso);
            aula = itemView.findViewById(R.id.aulCurso);
            sesion = itemView.findViewById(R.id.sesCurso);

            fondo = itemView.findViewById(R.id.fondoCurso);
            icon = itemView.findViewById(R.id.iconCurso);
            recyDias = itemView.findViewById(R.id.recyclerdia);
            adapterDias = new adapterDias(new ArrayList<>());
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyDias.setLayoutManager(layoutManager);
        }


        public void setDias(ArrayList<String> dias) {
            adapterDias.setListDias(dias);
            adapterDias.notifyDataSetChanged();
        }
    }
}
