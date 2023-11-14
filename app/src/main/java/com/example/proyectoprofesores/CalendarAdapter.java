package com.example.proyectoprofesores;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<com.example.proyectoprofesores.CalendarAdapter.ViewHolder> {

    private List<CalendarItem> calendarItems;
    private ArrayList<Evento> listaEventos;
    private RecyclerView recyclerViewE;
    private int selectedItemPosition = -1;
    private boolean inicio = true;
    private Context context;
    RelativeLayout noevento;
    ImageView lineaE;



    public CalendarAdapter(List<CalendarItem> calendarItems, ArrayList<Evento> listaEventos, RecyclerView recyclerViewE, Context context) {
        this.calendarItems = calendarItems;
        this.listaEventos = listaEventos;
        this.recyclerViewE = recyclerViewE;
        this.context = context;
        noevento = ((Activity) context).findViewById(R.id.noevento);
        lineaE = ((Activity) context).findViewById(R.id.lineaE);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fecha_layout, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CalendarItem item = calendarItems.get(position);

        holder.dayOfWeek.setText(item.getDayOfWeek());
        holder.dayNumber.setText(item.getDayNumber());

        if (item.isSelected()) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_outline_blue);
            holder.dayOfWeek.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_outline);
            holder.dayOfWeek.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }
        // Comprueba si el día actual es igual a la fecha de este elemento

        Calendar calendar = Calendar.getInstance();
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
        int todayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int todayMonth = calendar.get(Calendar.MONTH);
        int todayYear = calendar.get(Calendar.YEAR);

        if (item.getDayNumber().equals(String.valueOf(todayDay)) && item.getMonth() == todayMonth && item.getYear() == todayYear && inicio) {
            // Establece el estado seleccionado si es el día actual
            item.setSelected(true);
            selectedItemPosition=position;
            holder.itemView.setBackgroundResource(R.drawable.rectangle_outline_blue);
            holder.dayOfWeek.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            inicio = false;

            //actualizar eventos
            ArrayList<Evento> eventosF= Evento.filtrarPorDia(listaEventos, CalendarItem.obtenerNombreDiaSemanaBD(todayWeek));
            eventosF = (ArrayList<Evento>) Evento.filtrarEventosPasados(eventosF);
            EventoAdapter adapter =  new EventoAdapter(eventosF, context);
            recyclerViewE.setAdapter(adapter);

            if(eventosF.size() == 0){
                noevento.setVisibility(View.VISIBLE);
                lineaE.setVisibility(View.GONE);
            }



        }

        // Comprueba si el elemento actual es diferente del elemento seleccionado y no es el día actual
        if (position != selectedItemPosition && !item.isSelected()) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_outline);
            holder.dayOfWeek.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));

        }

        // Configura el OnClickListener para cada elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dayOfWeek = item.getDayOfWeekNumber();

                //RESTAURAR VISTA

                noevento.setVisibility(View.GONE);
                lineaE.setVisibility(View.VISIBLE);

                if (item.getDayNumber().equals(String.valueOf(todayDay)) && item.getMonth() == todayMonth && item.getYear() == todayYear){
                    //actualizar eventos
                    ArrayList<Evento> eventosF;
                    eventosF  = Evento.filtrarPorDia(listaEventos, CalendarItem.obtenerNombreDiaSemanaBD(dayOfWeek));
                    eventosF = (ArrayList<Evento>) Evento.filtrarEventosPasados(eventosF);
                    EventoAdapter adapter =  new EventoAdapter(eventosF, context);
                    recyclerViewE.setAdapter(adapter);
                    mostrarDetallesDeFecha(item);
                    mostrarDetallesDeFecha(item);

                    if(eventosF.size() == 0){
                        noevento.setVisibility(View.VISIBLE);
                        lineaE.setVisibility(View.GONE);
                    }

                }else {
                    //actualizar eventos
                    ArrayList<Evento> eventosF;
                    eventosF  = Evento.filtrarPorDia(listaEventos, CalendarItem.obtenerNombreDiaSemanaBD(dayOfWeek));
                    EventoAdapter adapter =  new EventoAdapter(eventosF, context);
                    recyclerViewE.setAdapter(adapter);
                    mostrarDetallesDeFecha(item);

                    if(eventosF.size() == 0){
                        noevento.setVisibility(View.VISIBLE);
                        lineaE.setVisibility(View.GONE);
                    }
                }




                if (!item.isSelected()) {
                    // Restablece el fondo del elemento previamente seleccionado
                    if (selectedItemPosition != -1) {
                        CalendarItem previouslySelectedItem = calendarItems.get(selectedItemPosition);
                        previouslySelectedItem.setSelected(false);
                        notifyItemChanged(selectedItemPosition);
                    }

                    // Marca el nuevo elemento como seleccionado
                    selectedItemPosition = position;
                    item.setSelected(true);
                    holder.itemView.setBackgroundResource(R.drawable.rectangle_outline_blue);
                    holder.dayOfWeek.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                    holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return calendarItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeek, dayNumber, monthName, year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.calendar_day);
            dayNumber = itemView.findViewById(R.id.calendar_number);

        }
    }

    // Método para mostrar los detalles de las fechas en el Activity
    private void mostrarDetallesDeFecha(CalendarItem item) {

        // Actualiza los elementos de la vista principal en el Activity
        TextView numberCalendar = ((Activity) context).findViewById(R.id.number_calendar);
        TextView weekDayCalendar = ((Activity) context).findViewById(R.id.weekDay_calendar);
        TextView monthCalendar = ((Activity) context).findViewById(R.id.month_calendar);
        TextView yearCalendar = ((Activity) context).findViewById(R.id.year_calendar);
        LinearLayout fondo = ((Activity) context).findViewById(R.id.linear_calendar);

        numberCalendar.setText(item.getDayNumber());
        weekDayCalendar.setText(item.getFullWeekName(item.getDayOfWeek()));
        monthCalendar.setText(item.getMonthName());
        yearCalendar.setText(String.valueOf(item.getYear()));



    }
}
