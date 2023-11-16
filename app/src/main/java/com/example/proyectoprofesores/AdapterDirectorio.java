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
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) 
        String alumno = "" + listDirectorio.get(position).nombreAlumno;
        holder.NombreAlumno.setText(alumno);

        holder.NombrePariente.setText(listDirectorio.get(position).nombrePariente);
        holder.NumeroPariente.setText(listDirectorio.get(position).numeroPariente);

        if (position % 2 == 0) {
            holder.FondoContacto.setImageResource(R.drawable.contact_box);
            holder.Contacto.setImageResource(R.drawable.boy__1_);
        } else {
            holder.FondoContacto.setImageResource(R.drawable.contact_box_azul);
            holder.Contacto.setImageResource(R.drawable.user_image);
        }
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
