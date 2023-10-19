package com.example.proyectoprofesores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private List<CalendarItem> calendarItems;
    private Context context;


    public CalendarAdapter(List<CalendarItem> calendarItems, Context context) {
        this.calendarItems = calendarItems;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fecha_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CalendarItem item = calendarItems.get(position);

        holder.dayOfWeek.setText(item.getDayOfWeek());
        holder.dayNumber.setText(item.getDayNumber());
       //holder.monthName.setText(item.getMonthName());
        //holder.year.setText(item.getYear());

        // Configura el OnClickListener para cada elemento

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


        numberCalendar.setText(item.getDayNumber());
        weekDayCalendar.setText(item.getFullWeekName(item.getDayOfWeek()));
        monthCalendar.setText(item.getMonthName());
        yearCalendar.setText(String.valueOf(item.getYear()));// Actualiza el mes como desees
    }
}
