package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
    String idCurso;
    TextView pageTitleTextView;
    String title, content, docId, codId, id;

    JsonObjectRequest jsonObjectRequest;

    private OnNoteSavedListener listener;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        pageTitleTextView = findViewById(R.id.page_title);
        noteList = new ArrayList<>();
        idUsuario= getIntent().getStringExtra("idUsuario");
        idDocente= getIntent().getStringExtra("idDocente");
        textoCurso= getIntent().getStringExtra("curso");
        idCurso = getIntent().getStringExtra("idCurso");
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        id = getIntent().getStringExtra("id");
        if(id!=null && !id.isEmpty()){
            isEditMode= true;
        }
        titleEditText.setText(title);
        contentEditText.setText(content);
        if(isEditMode){
            pageTitleTextView.setText("Editar nota");
        }
        saveNoteBtn.setOnClickListener((v)->{
                    cargarNotaService();
                    finish();
                });
        volver = findViewById(R.id.volverbt);
        volver.setOnClickListener(v -> finish());



    }

    private void cargarNotaService() {
        String ip = getString(R.string.ip);
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        String url;
        if(isEditMode){
            url = ip + "/update_notas.php?id="+ id+ "&titulo="+noteTitle+"&contenido="+noteContent;
            url=url.replace(" ", "%20");
            jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, this, this );
            //request.add(jsonArrayRequest);
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*4, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VoleySingleton.getIntanciaV(getApplicationContext()).addToRequestQueue(jsonObjectRequest);


        }else{
            url = ip + "/registrar_anotacion.php?titulo="+noteTitle+"&contenido="+noteContent+"&id_usuario="+idUsuario+"&id_cursos="+idCurso;
            url=url.replace(" ", "%20");
            jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, this, this );
            //request.add(jsonArrayRequest);
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*4, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VoleySingleton.getIntanciaV(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
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
        if(isEditMode){
            Toast.makeText(this, "Se ha editado exitosamente", Toast.LENGTH_SHORT).show();
            titleEditText.setText("");
            contentEditText.setText("");
            setResult(RESULT_OK, new Intent());
        }else{
            Toast.makeText(this, "Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
            titleEditText.setText("");
            contentEditText.setText("");
            setResult(RESULT_OK, new Intent());
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (isEditMode){
            Toast.makeText(this, "No se pudo editar"+error.toString(), Toast.LENGTH_SHORT).show();
            Log.i("ERROR: ", error.toString() );
        }else {
            Toast.makeText(this, "No se pudo registrar"+error.toString(), Toast.LENGTH_SHORT).show();
            Log.i("ERROR: ", error.toString() );
        }

    }


}