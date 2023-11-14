package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment implements OnAgendaClickListener, Response.Listener<JSONArray>, Response.ErrorListener{
    String idDocente;
    RelativeLayout directorio;
    private RecyclerView recyclerView;
    private RecyclerView recyclerEvento;
    ArrayList<Evento> listaEventos;
    JsonArrayRequest jsonArrayRequestEventos;
    ProgressBar progressBar;

    private List<CalendarItem> calendarItems;
    private CalendarAdapter adapterC;

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
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        // ... (código existente)
        Bundle args = getArguments();
        idDocente =args.getString("idDocente", "");

        listaEventos= new ArrayList<>();

        recyclerEvento = view.findViewById(R.id.recycleEvento_id);
        progressBar = view.findViewById(R.id.progress_bar);

        directorio = view.findViewById(R.id.titulo_directorio);
        directorio.setVisibility(View.GONE);

        recyclerEvento.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerEvento.setHasFixedSize(true);

        cargarWebServiceEventos();


        //recycle calendar
        recyclerView = view.findViewById(R.id.recyclerViewCalendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        calendarItems = new ArrayList<>();




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView number = view.findViewById(R.id.number_calendar);
        TextView weekDayName = view.findViewById(R.id.weekDay_calendar);
        TextView monthName = view.findViewById(R.id.month_calendar);
        TextView year = view.findViewById(R.id.year_calendar);

        Calendar calendar = Calendar.getInstance();
    //guardar el año y mes actual
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);

        number.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        weekDayName.setText(CalendarItem.getFullWeekName(obtenerNombreDiaSemana(calendar.get(Calendar.DAY_OF_WEEK))));
        monthName.setText(obtenerNombreMes(currentMonth));
        year.setText(String.valueOf(currentYear));


    }
    private void cargarWebServiceEventos(){
        progressBar.setVisibility(View.VISIBLE);
        String ip = getString(R.string.ip);
        String idDocenteURL ="?id_docente=" + idDocente;
        String url = ip + "/obtenerEventosCalendario.php" + idDocenteURL; //cambiar

        jsonArrayRequestEventos = new JsonArrayRequest(Request.Method.GET, url, null, this, this );
        //request.add(jsonArrayRequest);
        jsonArrayRequestEventos.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getContext()).addToRequestQueue(jsonArrayRequestEventos);
    }


    private void updateCalendar(int year) {
        calendarItems.clear();

        // Recorre todos los meses del año
        for (int month = 0; month < 12; month++) {
            // Obtén el primer día del mes actual
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, 1);

            // Número de días del mes actual
            int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            // Llena la lista con datos del calendario para este mes
            for (int i = 1; i <= maxDayOfMonth; i++) {
                calendar.set(year, month, i);
                int dayOfWeekNumber = calendar.get(Calendar.DAY_OF_WEEK);
                String dayOfWeek = obtenerNombreDiaSemana(calendar.get(Calendar.DAY_OF_WEEK));
                String monthName = obtenerNombreMes(month);
                String dayNumber = String.valueOf(i);

                calendarItems.add(new CalendarItem(dayOfWeek,dayOfWeekNumber, dayNumber, year, month, monthName));
            }
        }

        adapterC.notifyDataSetChanged();
    }

    private String obtenerNombreDiaSemana(int dayOfWeek) {
        String[] nombresDias = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        return nombresDias[dayOfWeek - 1];
    }
    private String obtenerNombreMes(int month){
        String[] monthName = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
        return monthName[month];
    }



    public void onAgendaClick(int position) {
        EventCursoFragment fragment = new EventCursoFragment();

        getParentFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void posicionActual(RecyclerView recycleCalendar){
        Calendar calendar = Calendar.getInstance();
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
        int todayMonth = calendar.get(Calendar.MONTH);

        int positionOfToday = -1;
        for (int i = 0; i < calendarItems.size(); i++) {
            CalendarItem item = calendarItems.get(i);
            if ((Integer.parseInt(item.getDayNumber()) == todayDay) && (item.getMonth() == todayMonth)) {
                positionOfToday = i;
                break;
            }
        }

        if (positionOfToday != -1) {
            // Establece la posición del día actual en la vista
            recycleCalendar.scrollToPosition(positionOfToday);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("Ayuda aquii:", error.toString());
        progressBar.setVisibility(View.GONE);



    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for(int i=0; i<response.length(); i++){
                JSONObject jsonObject = response.getJSONObject(i);
                Evento evento = new Evento(Integer.valueOf(jsonObject.optString("id_horario")), jsonObject.optString("curso"), jsonObject.optString("aula"), jsonObject.optString("nivel"),
                        jsonObject.optString("dia"), Time.valueOf(jsonObject.optString("horainicio")), Time.valueOf(jsonObject.optString("horafin")));

                listaEventos.add(evento);
            }
            progressBar.setVisibility(View.GONE);
            directorio.setVisibility(View.VISIBLE);


            //Evento.filtrarEventosPasados(listaEventos);
            Evento.ordenarListaPorHoraInicio(listaEventos);
            //Evento.ordenarListaPorProximidad(listaEventos);

            EventoAdapter adapter =  new EventoAdapter(listaEventos, getContext());
            adapter.setOnAgendaClickListener(this);
            recyclerEvento.setAdapter(adapter);

            //Adaptador Calendar

            adapterC = new CalendarAdapter(calendarItems, listaEventos, recyclerEvento, getContext());
            recyclerView.setAdapter(adapterC);

            Calendar calendar = Calendar.getInstance();
            currentYear = calendar.get(Calendar.YEAR);
            updateCalendar(currentYear);

            posicionActual(recyclerView);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexion con el servidor" + " " + response, Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }

    }
}