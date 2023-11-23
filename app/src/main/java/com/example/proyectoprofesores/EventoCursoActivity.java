package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventoCursoActivity extends AppCompatActivity {
    ImageView back;
    private String id_horario;
    private String curso;
    private String aula;
    private String dia;
    private String nivel;
    private String horaInicio;
    private String horaFin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_curso);
        back = findViewById(R.id.closeEv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        id_horario = getIntent().getStringExtra("idHorario");
        curso = getIntent().getStringExtra("cursos");
        aula = getIntent().getStringExtra("aula");
        dia = getIntent().getStringExtra("dia");
        nivel = getIntent().getStringExtra("nivel");
        horaInicio = getIntent().getStringExtra("horaInicio");
        horaFin = getIntent().getStringExtra("horaFin");
        TextView textCurso = findViewById(R.id.nameCurso);
        textCurso.setText(curso);
        TextView textFecha = findViewById(R.id.fecha_curso_detail_id);
        textFecha.setText(dia);
        TextView textHora = findViewById(R.id.fecha_hora_detail_id);
        String hr=horaInicio + " - "+horaFin;
        textHora.setText(hr);
        TextView txtSalon = findViewById(R.id.salontxt);
        String sal= "Salon "+aula;
        txtSalon.setText(sal);
        TextView txtMensaje = findViewById(R.id.txtmensjae);
        String mensaje= "¡Recuerde marcar su entrada con el QR del salón y salir a tiempo!";
        txtMensaje.setText(mensaje);
    }
}