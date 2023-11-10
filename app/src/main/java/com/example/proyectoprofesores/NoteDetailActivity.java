package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoteDetailActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    EditText titleEditText, contentEditText;
    ImageButton saveNoteBtn;
    ArrayList<notas> noteList;
    ImageView volver;
    String textoCurso;
    String idUsuario;
    String idDocente;

    JsonObjectRequest jsonObjectRequest;

    private OnNoteSavedListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        noteList = new ArrayList<>();
        saveNoteBtn.setOnClickListener((v)->{
                    cargarNotaService();
                    saveNote();
                });
        volver = findViewById(R.id.volverbt);
        volver.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        if (intent != null) {
            idUsuario= intent.getStringExtra("idUsuario");
            idDocente= intent.getStringExtra("idDocente");
            textoCurso= intent.getStringExtra("curso");
        }

        // Inicializa el listener aquí si es necesario
        listener = new CourseDescpFragment();

    }

    private void cargarNotaService() {
        String ip = getString(R.string.ip);
        String url = ip + "/obtener_justificaciones.php?titulo="+titleEditText.getText().toString()+"&contenido="+contentEditText.getText().toString();
        url=url.replace(" ", "%20");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, this, this );
        //request.add(jsonArrayRequest);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    private void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if(!noteTitle.isEmpty() && !noteContent.isEmpty()){
            listener.onNoteSaved(noteTitle, noteContent);
            finish();
        }
    }

    private void clearInputFields() {
        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }
    public void setOnNoteSavedListener(OnNoteSavedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
        titleEditText.setText("");
        contentEditText.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se pudo registrar"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR: ", error.toString() );
    }

    
}