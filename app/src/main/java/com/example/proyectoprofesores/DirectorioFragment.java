package com.example.proyectoprofesores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DirectorioFragment extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener {

    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    private SearchView searchView;
    private String mParam3;
    private String mParam4;
    private RecyclerView recyDir;
    ArrayList<Directorio> listDirectorio;

    private JsonArrayRequest jsonArrayRequest;

    private ProgressBar progressBar;
    String idUsuario;
    String idDocente;

    public DirectorioFragment() {
        // Constructor vacío requerido
    }

    public static DirectorioFragment newInstance(String param3, String param4) {
        DirectorioFragment fragment = new DirectorioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_directorio, container, false);
        listDirectorio = new ArrayList<>();
        Bundle args = getArguments();
        idUsuario = args.getString("idUsuario", "");
        idDocente =args.getString("idDocente", "");

        recyDir = vista.findViewById(R.id.recyclerViewDirectorio);
        progressBar = vista.findViewById(R.id.progress_bar2);
        recyDir.setLayoutManager(new LinearLayoutManager(getContext()));
        recyDir.setHasFixedSize(true);

        cargarWebService();

        searchView = vista.findViewById(R.id.busqueda_directorio);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar la lista cuando el texto del SearchView cambie
                ((AdapterDirectorio) recyDir.getAdapter()).filter(newText);
                return false;
            }
        });

        return vista;
    }

    private void cargarWebService() {
        progressBar.setVisibility(View.VISIBLE);
        String ip = getString(R.string.ip);
        String url = ip + "/obtener_Directorio.php?id_docente="+idDocente;

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        // Log the error for debugging
        Log.d("ERROR:", error.toString());
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);

                Directorio directorio = new Directorio.Builder()
                        .withNombreAlumno(jsonObject.optString("nombre"))
                        .withNombrePariente(jsonObject.optString("parentesco"))
                        .withNumeroPariente(jsonObject.optString("telefono"))
                        .withSexo(jsonObject.optString("sexo"))
                        .build();
                listDirectorio.add(directorio);
            }
            progressBar.setVisibility(View.GONE);
            AdapterDirectorio adapter = new AdapterDirectorio(listDirectorio, getContext());
            recyDir.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    }
}
