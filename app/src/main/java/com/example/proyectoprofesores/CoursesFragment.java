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
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class CoursesFragment extends Fragment implements OnCursoClickListener, Response.Listener<JSONArray>, Response.ErrorListener {
    ArrayList<cursodt> listCursos;
    RecyclerView recy;
    RecyclerView recyD;
    SearchView searchView;
    String selectedFilter = "todos";
    String currentSearchText = "";
    String idUsuario;
    String idDocente;
    JsonArrayRequest jsonArrayRequest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        idUsuario = args.getString("idUsuario", "");
        idDocente = args.getString("idDocente", "");

        if (args != null) {
            String textoSalon = args.getString("textoSalon", "");
            // Usa textoSalon como desees
        }
        recy = view.findViewById(R.id.recyclercurso);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setHasFixedSize(true);
        recyD = view.findViewById(R.id.recyclerdia);
        listCursos = new ArrayList<>();
        llenarCursos();
        adapterCursos adapter = new adapterCursos(getContext(), listCursos);
        adapter.setOnCursoClickListener(this);
        recy.setAdapter(adapter);
        adapterCursos.ViewHolderDatos viewHolder = (adapterCursos.ViewHolderDatos) recy.findViewHolderForAdapterPosition(0);

// Configura el RecyclerView de días
        if (viewHolder != null) {
            viewHolder.setDias(listCursos.get(0).getDias());
        }
        searchView = view.findViewById(R.id.busqueda_directorio);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Llama a un método que filtre tu lista de cursos
                filter(newText);
                return true;
            }
        });

        Button filtodos = view.findViewById(R.id.filtodos);
        filtodos.setOnClickListener(v -> {
            selectedFilter = "todos";
            searchView.setQuery("", false);
            searchView.clearFocus();
            adapterCursos filteredAdapter = new adapterCursos(getContext(), listCursos);
            filteredAdapter.setOnCursoClickListener(this);
            recy.setAdapter(filteredAdapter);
        });
        Button fillunes = view.findViewById(R.id.fillunes);
        fillunes.setOnClickListener(v -> filterList("lunes"));

        Button filmartes = view.findViewById(R.id.filmartes);
        filmartes.setOnClickListener(v -> filterList("martes"));

        Button filmierco = view.findViewById(R.id.filmierco);
        filmierco.setOnClickListener(v -> filterList("miercoles"));

        Button filjueves = view.findViewById(R.id.filjueves);
        filjueves.setOnClickListener(v -> filterList("jueves"));

        Button filviernes = view.findViewById(R.id.filviernes);
        filviernes.setOnClickListener(v -> filterList("viernes"));


    }

    private void llenarCursos() {
//        ArrayList<String> listDias = new ArrayList<>();
//        listDias.add("Lunes");
//        listDias.add("Martes");
//        listCursos.add(new cursodt(R.drawable.fondo_curso1, R.drawable.logo_math, "Trigonometria", "Secundaria", "3B", "25", listDias));
//        ArrayList<String> diasCurso2 = new ArrayList<>();
//        diasCurso2.add("Martes");
//        listCursos.add(new cursodt(R.drawable.fondo_curso2, R.drawable.logo_math, "Algebra", "Secundaria", "1A", "20", diasCurso2));
//        listCursos.add(new cursodt(R.drawable.fondo_curso3, R.drawable.logo_plant, "Biologia", "Secundaria", "5B", "22", listDias));
        String ip = getString(R.string.ip);
        String url = ip + "/obtener_cursos.php?user="+ idDocente;

        jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        //request.add(jsonArrayRequest);
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error al conectar con el servidor: " + error.toString(), Toast.LENGTH_LONG).show();
        Log.d("ERROR:", error.toString());
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            Toast.makeText(getContext(), "Si responde", Toast.LENGTH_LONG).show();
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                //Toast.makeText(getContext(), "Intento: " + i, Toast.LENGTH_LONG).show();

                // Extraer datos del objeto JSON
                String nombreCurso = jsonObject.getString("nombre_curso");
                String nivel = jsonObject.getString("nivel");
                String aula = jsonObject.getString("nombre_aula");
                String dia = jsonObject.getString("dia");

                // Crear una ArrayList para los días y agregar el día
                ArrayList<String> listDias = new ArrayList<>();
                listDias.add(dia);

                // Crear un nuevo objeto cursodt y agregarlo a listCursos
                listCursos.add(new cursodt(R.drawable.fondo_curso1, R.drawable.logo_math, nombreCurso, nivel, aula, "22", listDias));
            }

            // Utilizar listCursos según sea necesario en tu aplicación
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCursoClick(int position) {
        String textoCurso = listCursos.get(position).getNombre();
        String textoAula = listCursos.get(position).getAula();
        String textoNivel = listCursos.get(position).getNivel();


        CourseDescpFragment fragment = new CourseDescpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("aula", textoAula);
        bundle.putString("curso", textoCurso);
        bundle.putString("nivel", textoNivel);
        bundle.putString("idUsuario", idUsuario);
        bundle.putString("idDocente", idDocente);
        fragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void filter(String text) {
        currentSearchText = text;
        ArrayList<cursodt> filteredList = new ArrayList<>();

        for (cursodt curso : listCursos) {
            if (curso.getNombre().toLowerCase().contains(text.toLowerCase())) {
                if(selectedFilter.equals("todos")){
                    filteredList.add(curso);
                }else{
                    ArrayList<String> diasCurso = curso.getDias();

                    ArrayList<String> diasCursoLowerCase = new ArrayList<>();
                    for (String dia : diasCurso) {
                        diasCursoLowerCase.add(dia.toLowerCase()); // Convertir a minúsculas
                    }

                    if (diasCursoLowerCase.contains(selectedFilter)) {
                        filteredList.add(curso);
                    }
                }

            }
        }

        // Crea un nuevo adaptador con la lista filtrada y configura el RecyclerView
        adapterCursos filteredAdapter = new adapterCursos(getContext(), filteredList);
        filteredAdapter.setOnCursoClickListener(this);
        recy.setAdapter(filteredAdapter);
    }

    private void filterList(String status) {
        selectedFilter = status.toLowerCase(); // Convertir a minúsculas

        ArrayList<cursodt> filteredList = new ArrayList<>();
        for (cursodt curso : listCursos) {
            ArrayList<String> diasCurso = curso.getDias();

            ArrayList<String> diasCursoLowerCase = new ArrayList<>();
            for (String dia : diasCurso) {
                diasCursoLowerCase.add(dia.toLowerCase()); // Convertir a minúsculas
            }

            if (diasCursoLowerCase.contains(selectedFilter)) {
                if(Objects.equals(currentSearchText, "")){
                    filteredList.add(curso);
                }else{
                    if(curso.getNombre().toLowerCase().contains(currentSearchText.toLowerCase())){
                        filteredList.add(curso);
                    }
                }

            }
        }

        adapterCursos filteredAdapter = new adapterCursos(getContext(), filteredList);
        filteredAdapter.setOnCursoClickListener(this);
        recy.setAdapter(filteredAdapter);

    }

}
