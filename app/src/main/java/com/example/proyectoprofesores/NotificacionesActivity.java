package com.example.proyectoprofesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificacionesActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {

    RecyclerView recyNot;
    ArrayList<Notificaciones> listaNotificaciones;
    ImageView back;
    JsonArrayRequest jsonArrayRequest;

    ProgressBar progressBar;

    String idUsuario;
    String idDocente;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        Intent intent = getIntent();
        if (intent != null) {
            idUsuario= intent.getStringExtra("idUsuario");
            idDocente= intent.getStringExtra("idDocente");

        }
        back = findViewById(R.id.retro);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyNot = findViewById(R.id.recyclerViewNotificaciones);
        progressBar = findViewById(R.id.progress_bar3);
        recyNot.setLayoutManager(new LinearLayoutManager(this));
        recyNot.setHasFixedSize(true);

        listaNotificaciones = new ArrayList<>();

        cargarWebService();
    }

    private void cargarWebService() {
        progressBar.setVisibility(View.VISIBLE);
        String ip = getString(R.string.ip);
        String url = ip + "/obtener_notificacion.php?id_usuario="+idUsuario;

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        VoleySingleton.getIntanciaV(this).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        Log.d("ERROR:", error.toString());
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(JSONArray jsonArray) {
        try {
            listaNotificaciones.clear(); // Limpiar la lista antes de agregar nuevas notificaciones
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Notificaciones notificaciones = new Notificaciones.Builder()
                        .withImage(R.drawable.icon_branded_frame_32)
                        .withTitulo(jsonObject.optString("titulo"))
                        .withNoti(jsonObject.optString("descripcion"))
                        .build();
                listaNotificaciones.add(notificaciones);
            }
            progressBar.setVisibility(View.GONE);
            AdapterNotificaciones adapter = new AdapterNotificaciones(listaNotificaciones, this);
            recyNot.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se ha podido establecer conexión con el servidor", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    }
}
