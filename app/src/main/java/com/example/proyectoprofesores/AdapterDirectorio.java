package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDirectorio extends RecyclerView.Adapter<AdapterDirectorio.ViewHolderDatos>  {
    ArrayList<Directorio> listDirectorio;
    private ArrayList<Directorio> listDirectorioOriginal;
    Context context;

    public AdapterDirectorio(ArrayList<Directorio> listDirectorio, Context context) {
        this.listDirectorio = listDirectorio;
        this.listDirectorioOriginal = new ArrayList<>(listDirectorio);
        this.context = context;
    }

    public void filter(String text) {
        listDirectorio.clear();
        if (text.isEmpty()) {
            listDirectorio.addAll(listDirectorioOriginal);
        } else {
            text = text.toLowerCase();
            for (Directorio item : listDirectorioOriginal) {
                if (item.getNombreAlumno().toLowerCase().contains(text)) {
                    listDirectorio.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterDirectorio.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_contactos, parent, false);
        return new ViewHolderDatos(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position){
    String alumno = "" + listDirectorio.get(position).nombreAlumno;
        holder.NombreAlumno.setText(alumno);

        holder.NombrePariente.setText(listDirectorio.get(position).nombrePariente);
        holder.NumeroPariente.setText(listDirectorio.get(position).numeroPariente);
        int textColor=0;
        if (position % 2 == 0) {
            holder.FondoContacto.setImageResource(R.drawable.contact_box);
            textColor = ContextCompat.getColor(context, R.color.black);
        } else {
            holder.FondoContacto.setImageResource(R.drawable.contact_box_azul);
            textColor = ContextCompat.getColor(context, R.color.colorBlanco);
        }

        Directorio directorio = listDirectorio.get(position);
        holder.Contacto.setImageResource(directorio.getBackgroundImageResource());
        holder.NombreAlumno.setTextColor(textColor);
        holder.NombrePariente.setTextColor(textColor);
        holder.NumeroPariente.setTextColor(textColor);

    }

    @Override
    public int getItemCount() {
        return listDirectorio.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView Contacto, FondoContacto;
        TextView NombreAlumno, NombrePariente, NumeroPariente;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Contacto = itemView.findViewById(R.id.imageViewContacto);
            NombreAlumno = itemView.findViewById(R.id.textViewNombreAlumno);
            NombrePariente = itemView.findViewById(R.id.textViewNombrePariente);
            NumeroPariente = itemView.findViewById(R.id.textViewNumeroPariente);
            FondoContacto = itemView.findViewById(R.id.imageViewFondo);

        }
    }


}
