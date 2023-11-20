    package com.example.proyectoprofesores;
    
    import android.app.ProgressDialog;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.preference.PreferenceManager;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;
    
    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;
    
    import com.android.volley.AuthFailureError;
    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;
    import com.android.volley.DefaultRetryPolicy;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.example.proyectoprofesores.VoleySingleton;
    
    import org.json.JSONException;
    import org.json.JSONObject;
    import org.w3c.dom.Text;
    
    import java.util.HashMap;
    import java.util.Map;
    
    public class PerfilActivity extends AppCompatActivity {
        ImageView back,profile;
        Button actualizar,logout;
        EditText edTutor,edCorreo,edID;
        TextView edNombre,tNombre,tCorreo,edApellido,edCurso;
    
        String idUsuario;
        String idDocente;
        String nombre;
        String apellido;
        String correo;
        String tutor;
        String curso;
        String user;

        int prueba;
    
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_perfil);
            back = findViewById(R.id.backB);
            profile = findViewById(R.id.imageView6);
            logout = findViewById(R.id.button_cerrar_sesion);
            actualizar =findViewById(R.id.button_actualilzar);
            edNombre=findViewById(R.id.editTextNombre);
            edApellido=findViewById(R.id.editTextApellido);
            edCurso=findViewById(R.id.editTextCursos);
            edTutor=findViewById(R.id.editTextTutor);
            edCorreo=findViewById(R.id.editTextCorreo);
            edID=findViewById(R.id.editTextID);
            tNombre=findViewById(R.id.textNombre);
            tCorreo=findViewById(R.id.textCorreo);
    
            Intent intent=getIntent();
            if (intent != null && intent.getExtras() != null) {
                Bundle bundle = intent.getExtras();
                idUsuario = bundle.getString("idUsuario");
                idDocente = bundle.getString("idDocente");
                nombre = bundle.getString("nombre");
                apellido = bundle.getString("apellido");
                correo = bundle.getString("correo");
                //aulaTuto = bundle.getString("aulaTuto");
    
                mostrarDatosFaltantes();
                profile.setImageResource(R.drawable.logo_hombre);
                // Usa los valores según sea necesario
                edNombre.setText(nombre);
                edApellido.setText(apellido);
                edCorreo.setText(correo);
                tNombre.setText(nombre);
                tCorreo.setText(correo);
            }
    
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
    
                    finish();
                }
            });
    
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cerrar_sesion();
                }
            });

            recuperarDatosDeSharedPreferences();
        }

        public void mostrarDatosFaltantes(){
            String ip = getString(R.string.ip);
            String url = ip + "/actualizar_perfil.php?user=" + idDocente;
    
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    response -> {
                        try {
                            // Supongo que la respuesta del servidor es un solo objeto JSON, ajusta según tu respuesta
                            JSONObject jsonObject = response.getJSONObject(0);

                            // Obtén los datos del perfil y guárdalos en las variables
                            tutor = jsonObject.optString("nombre_aula");
                            curso = jsonObject.optString("nombre_curso");
                            user = jsonObject.optString("cod_usuario");

                            edCurso.setText(curso);
                            edTutor.setText(tutor);
                            edID.setText(user);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error al obtener datos del perfil", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Toast.makeText(getApplicationContext(), "No se puede conectar: " + error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("ERROR:", error.toString());
                    }
            );
    
            // Establece la política de reintento para la solicitud
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
    
            // Agrega la solicitud a la cola de Volley
            VoleySingleton.getIntanciaV(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
        }
    
        public void actualizar(View view){
            String Correo = edCorreo.getText().toString().trim();
            String Curso = edCurso.getText().toString().trim();
            String Id = edID.getText().toString().trim();
            String Tutor = edTutor.getText().toString().trim();
            // Otros campos que deseas actualizar
    
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Actualizando");
            progressDialog.show();
    
            StringRequest request = new StringRequest(Request.Method.POST, "https://fzac2311.000webhostapp.com/actualizar_perfil.php",
                    response -> {
                        Toast.makeText(getApplicationContext(), "Actualizado con éxito", Toast.LENGTH_SHORT).show();

                        tutor = Tutor;
                        curso = Curso;
                        user = Id;

                        edCurso.setText(curso);
                        edTutor.setText(tutor);
                        edID.setText(user);

                        guardarDatosEnSharedPreferences();

                        progressDialog.dismiss();
                        mostrarDatosFaltantes();
                    },
                    error -> {
                        Log.e("Error", "Error al actualizar", error);
                        Toast.makeText(PerfilActivity.this, "Error al actualizar: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("id",idDocente);
                    params.put("correo", Correo);
                    params.put("tutor", Tutor);
                    params.put("usuario", Id);
                    return params;
                }
            };
    
            RequestQueue requestQueue = Volley.newRequestQueue(PerfilActivity.this);
            requestQueue.add(request);
        }


        private void cerrar_sesion(){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("idUsuario");
            editor.clear();
            editor.apply();
    
            // Finaliza la actividad actual
            finish();
    
            // Configura la intención para ir a IntroActivity y borrar otras actividades de la pila
            Intent intent = new Intent(this, IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    
            // Inicia IntroActivity
            startActivity(intent);
        }

        private void guardarDatosEnSharedPreferences() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("tutor", tutor);
            editor.putString("curso", curso);
            editor.putString("usuario", user);
            editor.apply();
        }

        private void recuperarDatosDeSharedPreferences() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            tutor = preferences.getString("tutor", "");
            curso = preferences.getString("curso", "");
            user = preferences.getString("usuario", "");

            // Actualizar campos con los datos recuperados
            edCurso.setText(curso);
            edTutor.setText(tutor);
            edID.setText(user);
        }
    }

