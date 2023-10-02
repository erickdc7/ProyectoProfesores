package com.example.proyectoprofesores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterDias extends RecyclerView.Adapter<adapterDias.ViewHolderDatos> {
    private ArrayList<String> listDias;

    public adapterDias(ArrayList<String> listDias) {
            this.listDias = listDias;
            }

    @NonNull
    @Override
    public adapterDias.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dia, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterDias.ViewHolderDatos holder, int position) {
            holder.dia.setText(listDias.get(position));
            }

    @Override
    public int getItemCount() {
            return listDias.size();
            }

    public void setListDias(ArrayList<String> dias) {
        this.listDias = listDias;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dia;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.diaCurso);
        }
    }
}
