package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {
    EditText pass;
    EditText user;
    Button button_enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        pass= findViewById(R.id.TextC);
        user= findViewById(R.id.TextE);
        button_enter = findViewById(R.id.button_enter);

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario(v);
            }
        });
    }

    public void registrarUsuario(View view) {
        final String codigoUsuario = user.getText().toString().trim();
        final String contrasena = pass.getText().toString().trim();
        final String contrasenaEncriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        // Encriptar la contraseña usando BCrypt
        String ip = getString(R.string.ip);
        // Enviar la contraseña encriptada al servidor
        String url = ip + "/regis_us.php?user="+ codigoUsuario + "ANDcontra="+ contrasenaEncriptada;

        Log.d("Password Verification", "2 Password: " + contrasenaEncriptada);// Reemplaza con la URL correcta
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String mensaje = jsonObject.getString("mensaje");
                            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user", codigoUsuario);
                params.put("contra", contrasenaEncriptada);
                return params;
            }

        };

        // Añadir la solicitud a la cola de Volley
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*4, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}