package com.example.proyectoprofesores;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDescpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDescpFragment extends Fragment implements OnNoteClickListener, Response.Listener<JSONArray>, Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PREFS_NAME = "NotePrefs";
    private static final String KEY_NOTE_COUNT = "NoteCount";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView backp;
    ArrayList<notas>  noteList = new ArrayList<>();
    Button botonInsertarNotas;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NoteAdapter adapter;

    String textoCurso;
    String idUsuario;
    String idDocente;
    String idCurso = "9";

    JsonArrayRequest jsonArrayRequest;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDescpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDescpFragment newInstance(String param1, String param2) {
        CourseDescpFragment fragment = new CourseDescpFragment();
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

    private final ActivityResultLauncher<Intent> noteDetailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Handle the result if needed
                    noteList.clear();
                    cargarWebService();
                }
            }
    );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_descp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backp = view.findViewById(R.id.closeCur);
        backp.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        Bundle args = getArguments();
        idUsuario = args.getString("idUsuario", "");
        idDocente = args.getString("idDocente", "");

        if(args!=null){
            textoCurso = args.getString("curso", "");
            TextView textCour = view.findViewById(R.id.titleC);
            textCour.setText(textoCurso);
            // Usa textoSalon como desees
        }


        botonInsertarNotas = view.findViewById(R.id.botonInsertarNotas);
        botonInsertarNotas.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), NoteDetailActivity.class);
            intent.putExtra("curso", textoCurso);
            intent.putExtra("idUsuario", idUsuario);
            intent.putExtra("idDocenete", idDocente);
            intent.putExtra("idCurso", idCurso);
            noteDetailLauncher.launch(intent);
        });


        recyclerView = view.findViewById(R.id.recy_note);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        noteList.clear();
        cargarWebService();


    }
    private void cargarWebService() {
        String ip = getString(R.string.ip);
        String url = ip + "/obtener_notas.php?id_usuario="+idUsuario+"&id_cursos="+idCurso;

        jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, this, this );
        //request.add(jsonArrayRequest);
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getContext()).addToRequestQueue(jsonArrayRequest);
    }
    @Override
    public void onNoteClick(int position) {
        String id = String.valueOf(noteList.get(position).getId());
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Eliminar esta nota.");
        builder.setMessage("¿Esta seguro de querer eliminar esta nota?");
        builder.setPositiveButton("Delete", (dialog, which) -> {eliminarWebService(id);
            noteList.clear();
            cargarWebService();});
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }
    private void eliminarWebService(String id) {
        String ip = getString(R.string.ip);
        String url = ip + "/eliminar_notas.php?id="+ id;
        StringRequest request =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equalsIgnoreCase("elimina")){
                    Toast.makeText(getContext(), "Se ha eliminado con exito", Toast.LENGTH_SHORT).show();
                }else{
                    if (response.trim().equalsIgnoreCase("noExiste")) {
                        Toast.makeText(getContext(), "No se encuentra la nota", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getContext(), "No se ha eliminado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se ha podido conectar", Toast.LENGTH_SHORT).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getContext()).addToRequestQueue(request);
    }


    @Override
    public void onResponse(JSONArray response) {
        try {
            noteList.clear();
            for(int i=0; i<response.length(); i++){
                JSONObject jsonObject = response.getJSONObject(i);

                notas nota =  new notas();
                nota.setId(Integer.parseInt(jsonObject.optString("id_anotaciones")));
                nota.setTitle(jsonObject.optString("titulo"));
                nota.setContent(jsonObject.optString("descripcion"));
                nota.setFecha(jsonObject.optString("fecha"));
                nota.setHora(jsonObject.optString("hora"));
                noteList.add(nota);
            }
            if (adapter != null) {
                adapter.updateData(noteList);
                adapter.notifyDataSetChanged();
                Log.d("CourseDescpFragment", "onResponse: Data updated successfully.");
            } else {
                adapter = new NoteAdapter(getContext(), noteList);
                adapter.setOnNoteClickListener(this);
                recyclerView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexion con el servidor" + " " + response, Toast.LENGTH_LONG).show();

        }

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR:", error.toString());

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        noteList.clear();
        cargarWebService();
        Log.d("CourseDescpFragment", "onResponse: Data updated successfully.");
    }


}