package com.example.proyectoprofesores;

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
    public EventoAdapter(ArrayList<Evento> listDatos) {
        this.listDatos = listDatos;
    }
    OnAgendaClickListener listener;

    public void setOnAgendaClickListener(OnAgendaClickListener listener) {
        this.listener = listener;
    }

    public ArrayList<Evento> getListDatos() {
        return listDatos;
    }

    public OnAgendaClickListener getListener() {
        return listener;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

        if (position == 0) {
            // Cambiar el fondo del primer elemento
            holder.containerLayout.setImageResource(R.drawable.curso_agenda_primero);
            holder.iconoubicacion.setImageResource(R.drawable.icon_location);
            holder.mas.setImageResource(R.drawable.icon_mas_light);
            holder.horaInicio.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.horaFin.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.cursoTema.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.cursoNombre.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.lugar.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            // Restablecer el fondo para otros elementos
            holder.containerLayout.setImageResource(R.drawable.curso_agenda);
            holder.iconoubicacion.setImageResource(R.drawable.icon_location_dark);
            holder.mas.setImageResource(R.drawable.icon_mas_dark);
            holder.horaInicio.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.horaFin.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.cursoTema.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.cursoNombre.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.lugar.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }
        holder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                listener.onAgendaClick(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView containerLayout;
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
            cursoTema= itemView.findViewById(R.id.curso_tema_agemda);
            lugar= itemView.findViewById(R.id.lugar_id);
        }
        public void asignarDatos(Evento dato) {
            horaInicio.setText(dato.getHoraInicio());
            horaFin.setText(dato.getHoraFin());
            cursoNombre.setText(dato.getNombreCurso());
            cursoTema.setText(dato.getTemaCurso());
            lugar.setText(dato.getLugar());
        }
    }


}
