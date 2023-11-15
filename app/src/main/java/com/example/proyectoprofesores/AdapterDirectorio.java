package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterDirectorio extends RecyclerView.Adapter<AdapterDirectorio.ViewHolderDatos> {

    ArrayList<Directorio> listDirectorio;
    Context context;

    public AdapterDirectorio(ArrayList<Directorio> listDirectorio, Context context) {
        this.listDirectorio = listDirectorio;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDirectorio.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_contactos, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        // Use Glide to load images into ImageView
        Glide.with(context).load(listDirectorio.get(position).image).into(holder.Contacto);
        Glide.with(context).load(listDirectorio.get(position).imagefondo).into(holder.FondoContacto);

        holder.NombreAlumno.setText(listDirectorio.get(position).nombre);
        holder.NumeroMadre.setText(listDirectorio.get(position).num_Madre);
        holder.NumeroPadre.setText(listDirectorio.get(position).num_Padre);
    }

    @Override
    public int getItemCount() {
        return listDirectorio.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView Contacto, FondoContacto;
        TextView NombreAlumno, NumeroMadre, NumeroPadre;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Contacto = itemView.findViewById(R.id.imageViewContacto);
            NombreAlumno = itemView.findViewById(R.id.textViewNombreAlumno);
            NumeroMadre = itemView.findViewById(R.id.textViewNumeroMadre);
            NumeroPadre = itemView.findViewById(R.id.textViewNumeroPadre);
            FondoContacto = itemView.findViewById(R.id.imageViewFondo);
        }
    }
}



