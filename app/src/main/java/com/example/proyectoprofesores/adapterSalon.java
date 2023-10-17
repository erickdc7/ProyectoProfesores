package com.example.proyectoprofesores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterSalon extends RecyclerView.Adapter<adapterSalon.ViewHolderDatos> {
    Context context;
    ArrayList<salondt> listSalones;
    OnSalonClickListener listener;
    public void setOnSalonItemClickListener(OnSalonClickListener listener) {
        this.listener = listener;
    }
    public adapterSalon(Context context, ArrayList<salondt> listSalones) {
        this.context = context;
        this.listSalones = listSalones;
    }

    public adapterSalon(Context context, ArrayList<salondt> listSalones, OnSalonClickListener listener) {
        this.context = context;
        this.listSalones = listSalones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_salon, parent,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        String aulas = listSalones.get(position).getGrado()+listSalones.get(position).getSeccion();
        holder.fondo.setImageResource(listSalones.get(position).getFondo());
        holder.aula.setText(aulas);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                listener.onSalonClick(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSalones.size();
    }
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView fondo;
        TextView aula;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            aula=itemView.findViewById(R.id.textSalon);
            fondo = itemView.findViewById(R.id.salonview);

        }

    }
}
