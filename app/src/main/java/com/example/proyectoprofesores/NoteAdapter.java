package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolderDatos> {

    Context context;
    ArrayList<notas> listNotas;

    OnNoteClickListener listener;

    public void setOnNoteClickListener(OnNoteClickListener listener){
        this.listener=listener;
    }

    public NoteAdapter(Context context, ArrayList<notas> listNotas) {
        this.context = context;
        this.listNotas = listNotas;
    }

    public NoteAdapter(Context context, ArrayList<notas> listNotas, OnNoteClickListener listener) {
        this.context = context;
        this.listNotas = listNotas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        String fecha= listNotas.get(position).getFecha();
        String hora = listNotas.get(position).getHora();
        String fechaLetras = convertirFecha(fecha);
        String horaFormateada = formatearHora(hora);
        Date fechaActual = new Date();

        Date fechaHoraNota;
        try {
            fechaHoraNota = obtenerFechaHoraNota(fecha, hora);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (haPasado24Horas(fechaHoraNota, fechaActual)) {

            holder.time.setText(fechaLetras);
        } else {
            // Si no han pasado 24 horas, muestra la hora
            holder.time.setText(horaFormateada);
        }
        holder.titulo.setText(listNotas.get(position).getTitle());
        holder.content.setText(listNotas.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return listNotas.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo, content, time;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.titleTextView);
            content=itemView.findViewById(R.id.contectTextView);
            time = itemView.findViewById(R.id.timeTextView);

        }
    }

    private static boolean haPasado24Horas(Date fechaHoraNota, Date fechaActual) {
        // Calcula la diferencia en milisegundos
        long diferenciaMillis = fechaActual.getTime() - fechaHoraNota.getTime();

        // Calcula la diferencia en horas
        long diferenciaHoras = diferenciaMillis / (60 * 60 * 1000);

        // Retorna true si han pasado más de 24 horas
        return diferenciaHoras > 24;
    }

    private static String formatearHora(String hora) {
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            Date horaDate = formatoEntrada.parse(hora);

            if (horaDate != null) {
                SimpleDateFormat formatoSalida = new SimpleDateFormat("HH:mm", Locale.getDefault());
                return formatoSalida.format(horaDate);
            } else {
                return "No se pudo formatear";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "No se pudo formatear";
        }
    }

    private static Date obtenerFechaHoraNota(String fecha, String hora) throws ParseException {
        try {
            // Formato original de la fecha
            SimpleDateFormat formatoFechaOriginal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date fechaDate = formatoFechaOriginal.parse(fecha);

            // Verifica si la hora no es null
            if (fechaDate != null) {
                if (hora != null) {
                    // Formato original de la hora
                    SimpleDateFormat formatoHoraOriginal = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    Date horaDate = formatoHoraOriginal.parse(hora);

                    // Combina la fecha y la hora utilizando Calendar
                    if (horaDate != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaDate);
                        Calendar horaCalendar = Calendar.getInstance();
                        horaCalendar.setTime(horaDate);

                        // Establece las horas, minutos y segundos
                        calendar.set(Calendar.HOUR_OF_DAY, horaCalendar.get(Calendar.HOUR_OF_DAY));
                        calendar.set(Calendar.MINUTE, horaCalendar.get(Calendar.MINUTE));
                        calendar.set(Calendar.SECOND, horaCalendar.get(Calendar.SECOND));

                        fechaDate = calendar.getTime();
                    }
                }
            }
            return fechaDate;
        } catch (ParseException e) {
            e.printStackTrace();
            // Manejar el error lanzando una excepción o devolviendo una fecha por defecto
            throw new ParseException("Error al analizar la fecha", 0);
        }
    }


    private static String convertirFecha(String fecha) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat formatoSalida = new SimpleDateFormat("MMMM dd", Locale.getDefault());

        try {
            Date fechaDate = formatoEntrada.parse(fecha);

            if (fechaDate != null) {
                return formatoSalida.format(fechaDate);
            } else {
                // Manejar el caso en que fechaDate sea nulo
                return "No se pudo formatear";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "No se pudo formatear";
        }
    }

    public void updateData(ArrayList<notas> newlistNotas) {
        this.listNotas = newlistNotas;
        notifyDataSetChanged();
    }

}
