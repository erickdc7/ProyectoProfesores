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
import android.widget.ImageView;
import android.widget.ProgressBar;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JustifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JustifyFragment extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyFal;
    ArrayList<JustiFaltas> listFaltas;

    //RequestQueue request;
    JsonArrayRequest jsonArrayRequest;

    ProgressBar progressBar;
    ImageView backp;
    public JustifyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JustifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JustifyFragment newInstance(String param1, String param2) {
        JustifyFragment fragment = new JustifyFragment();
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
        View vista = inflater.inflate(R.layout.fragment_justify, container, false);
        listFaltas= new ArrayList<>();

        recyFal = vista.findViewById(R.id.recy_justi);
        progressBar = vista.findViewById(R.id.progress_bar);
        recyFal.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyFal.setHasFixedSize(true);

        //request= Volley.newRequestQueue(getContext());

        cargarWebService();

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backp = view.findViewById(R.id.imageatras);
        backp.setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }

    private void cargarWebService() {
        progressBar.setVisibility(View.VISIBLE);
        String ip = getString(R.string.ip);
        String url = ip + "/obtener_justificaciones.php";

        jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, this, this );
        //request.add(jsonArrayRequest);
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR:", error.toString());
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(JSONArray response) {

        try {
            for(int i=0; i<response.length(); i++){
                JSONObject jsonObject = response.getJSONObject(i);

                JustiFaltas justiFaltas =  new JustiFaltas.Builder()
                        .withNomAlumno(jsonObject.optString("alumno"))
                        .withAula(jsonObject.optString("aula"))
                        .withDescripcion(jsonObject.optString("descripcion"))
                        .build();
                listFaltas.add(justiFaltas);
            }
            progressBar.setVisibility(View.GONE);
            JustiFalAdapter adapter =  new JustiFalAdapter(listFaltas, getContext());
            recyFal.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexion con el servidor" + " " + response, Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }

    }
}