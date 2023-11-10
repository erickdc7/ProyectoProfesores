package com.example.proyectoprofesores;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class IntroActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        button = findViewById(R.id.button_intro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(IntroActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        String olvidaste=getString(R.string.recuperar_contra);
        String aqui=getString(R.string.ingresa_aqui);
        String todo= olvidaste + " " + aqui;
        SpannableString ss= new SpannableString(todo);
        int colorP = ContextCompat.getColor(this,R.color.amarillo);
        ss.setSpan(new ForegroundColorSpan(colorP), 25, todo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        TextView textView=findViewById(R.id.text_contra);
        textView.setText(ss);


    }

    public void MensajeAdvertencia(View mensaje){
        Toast.makeText(this, R.string.por_crear, Toast.LENGTH_SHORT).show();
    }

}
