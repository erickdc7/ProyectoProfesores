package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderDatos> {
    ArrayList<Evento> listDatos;
    Context context;
    OnCursoClickListener listener;

    public void setOnAgendaClickListener(OnCursoClickListener listener) {
        this.listener = listener;
    }
    public EventoAdapter(ArrayList<Evento> listDatos, Context context) {
        this.listDatos = listDatos;
        this.context = context;
    }

    public EventoAdapter(ArrayList<Evento> listDatos, Context context, OnCursoClickListener listener) {
        this.listDatos = listDatos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evento,parent,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

        if (position == 0) {
            // Cambiar el fondo del primer elemento
            holder.containerLayout.setBackgroundResource(R.drawable.curso_agenda_primero);
            holder.iconoubicacion.setImageResource(R.drawable.icon_location);
            holder.mas.setImageResource(R.drawable.icon_mas_light);
            holder.horaInicio.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.horaFin.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.cursoTema.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.cursoNombre.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.lugar.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    listener.onCursoClick(adapterPosition);
                }
            });
        } else {
            // Restablecer el fondo para otros elementos
            holder.containerLayout.setBackgroundResource(R.drawable.curso_agenda);
            holder.iconoubicacion.setImageResource(R.drawable.icon_location_dark);
            holder.mas.setImageResource(R.drawable.icon_mas_dark);
            holder.horaInicio.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.horaFin.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.cursoTema.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.cursoNombre.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.lugar.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    listener.onCursoClick(adapterPosition);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        RelativeLayout containerLayout;
        ImageView iconoubicacion;
        ImageView mas;

        TextView horaInicio;
        TextView horaFin;
        TextView cursoNombre;
        TextView cursoTema;
        TextView lugar;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            containerLayout = itemView.findViewById(R.id.cuadro_horario);
            iconoubicacion = itemView.findViewById(R.id.location_id_agenda);
            mas = itemView.findViewById(R.id.mas_id);
            horaInicio= itemView.findViewById(R.id.hora_inicio);
            horaFin = itemView.findViewById(R.id.hora_fin);
            cursoNombre= itemView.findViewById(R.id.curso_nombre_agenda);
            cursoTema= itemView.findViewById(R.id.curso_nivel_agemda);
            lugar= itemView.findViewById(R.id.lugar_id);
        }
        public void asignarDatos(Evento dato) {
            horaInicio.setText(Evento.convertirTiempoAstring(dato.getHoraInicio()));
            horaFin.setText(Evento.convertirTiempoAstring(dato.getHoraFin()));
            cursoNombre.setText(dato.getCurso());
            cursoTema.setText(dato.getNivel());
            lugar.setText(dato.getAula());
        }
    }


}
