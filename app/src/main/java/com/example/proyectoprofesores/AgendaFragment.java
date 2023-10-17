package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerEvento;
    ArrayList<Evento> listaEventos;
    private List<CalendarItem> calendarItems;
    private CalendarAdapter adapter;

    private int currentYear; // Año actual
    private int currentMonth; // Mes actual
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewCalendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        calendarItems = new ArrayList<>();
        adapter = new CalendarAdapter(calendarItems, getContext());
        recyclerView.setAdapter(adapter);
        TextView number = view.findViewById(R.id.number_calendar);
        TextView weekDayName = view.findViewById(R.id.weekDay_calendar);
        TextView monthName = view.findViewById(R.id.month_calendar);
        TextView year = view.findViewById(R.id.year_calendar);


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
        recyclerEvento = view.findViewById(R.id.recycleEvento_id);
        recyclerEvento.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

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