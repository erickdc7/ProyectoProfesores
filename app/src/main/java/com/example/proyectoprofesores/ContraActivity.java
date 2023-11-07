package com.example.proyectoprofesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.mindrot.jbcrypt.BCrypt;

public class ContraActivity extends AppCompatActivity{
    Button button;
    EditText editText;

    JSONArray jsonArray;
    String user;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrasena);
        progressBar = findViewById(R.id.progres_bar);
        button = findViewById(R.id.button_contra);
        editText = findViewById(R.id.editTextContra);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USER")) {
            user= intent.getStringExtra("USER");

            // Ahora puedes usar el valor del email en esta actividad
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contra = editText.getText().toString().trim();
                if (contra.isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.completar_espacio, Toast.LENGTH_SHORT).show();
                } else{
                    verificarusuario();
                }

            }
        });

        String olvidaste=getString(R.string.recuperar_contra);
        SpannableString ss= new SpannableString(olvidaste);
        int colorP = ContextCompat.getColor(this,R.color.amarillo);
        ss.setSpan(new ForegroundColorSpan(colorP), 0, olvidaste.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        TextView textView=findViewById(R.id.text_contra);
        textView.setText(ss);
    }

    public void MensajeAdvertencia(View mensaje){
        Toast.makeText(this, R.string.por_crear, Toast.LENGTH_SHORT).show();
    }

    private void verificarusuario() {
        String ip = getString(R.string.ip);
        String url = ip + "/login1.php?user="+ user;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    String pass = jsonArray.getString(0);
                    Log.d("Password Verification", "1 Password: " + pass);
                    String inputPassword = editText.getText().toString();
                    final String contrasenaEncriptada = BCrypt.hashpw(inputPassword, BCrypt.gensalt());
                    Log.d("Password Verification", "2 Password: " + contrasenaEncriptada);
                    if (verifyPassword(inputPassword, pass)) {
                        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ContraActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VoleySingleton.getIntanciaV(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        Log.d("Password Verification", "Input Password: " + inputPassword);
        Log.d("Password Verification", "Hashed Password: " + hashedPassword);
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

}
