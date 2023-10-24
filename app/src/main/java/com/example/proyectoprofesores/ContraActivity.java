package com.example.proyectoprofesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ContraActivity extends AppCompatActivity{
    Button button;
    EditText editText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrasena);

        button = findViewById(R.id.button_contra);
        editText = findViewById(R.id.editTextContra);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.completar_espacio, Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(ContraActivity.this, MainActivity.class);
                    startActivity(intent);
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
}
