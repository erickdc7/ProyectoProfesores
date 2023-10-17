package com.example.proyectoprofesores;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerEvento;
    ArrayList<Evento> listaEventos;
    private List<CalendarItem> calendarItems;
    private CalendarAdapter adapter;

    private int currentYear; // Año actual
    private int currentMonth; // Mes actual
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        recyclerView = findViewById(R.id.recyclerViewCalendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        calendarItems = new ArrayList<>();
        adapter = new CalendarAdapter(calendarItems, this);
        recyclerView.setAdapter(adapter);

        TextView number = findViewById(R.id.number_calendar);
        TextView weekDayName = findViewById(R.id.weekDay_calendar);
        TextView monthName = findViewById(R.id.month_calendar);
        TextView year = findViewById(R.id.year_calendar);


        // Inicializar con el año y mes actual
        Calendar calendar = Calendar.getInstance();



        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);

        number.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        weekDayName.setText(CalendarItem.getFullWeekName(obtenerNombreDiaSemana(calendar.get(Calendar.DAY_OF_WEEK))));
        monthName.setText(obtenerNombreMes(currentMonth));
        year.setText(String.valueOf(currentYear));

        updateCalendar(currentYear, currentMonth);

        //Evento
        recyclerEvento = (RecyclerView) findViewById(R.id.recycleEvento_id);
        recyclerEvento.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listaEventos = new ArrayList<Evento>();
        for(int i = 0; i <=10; i++){
            listaEventos.add(new Evento("1:00", "2:00", "cursito", "leer para aprender", "casita"));
        }
       EventoAdapter adapterE = new EventoAdapter(listaEventos);
        recyclerEvento.setAdapter(adapterE);

    }
    private void updateCalendar(int year, int month) {
        calendarItems.clear();

        // Obtén el primer día del mes
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        //int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Llena la lista con datos del calendario
        for (int i = 1; i <= maxDayOfMonth; i++) {
            calendar.set(year, month, i);
            String dayOfWeek = obtenerNombreDiaSemana(calendar.get(Calendar.DAY_OF_WEEK));
            String monthName = obtenerNombreMes(currentMonth);
            String dayNumber = String.valueOf(i);

            calendarItems.add(new CalendarItem(dayOfWeek, dayNumber, year, month, monthName));
        }

        adapter.notifyDataSetChanged();
    }

    private String obtenerNombreDiaSemana(int dayOfWeek) {
        String[] nombresDias = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        return nombresDias[dayOfWeek - 1];
    }
    private String obtenerNombreMes(int month){
        String[] monthName = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
        return monthName[month];
    }


}
