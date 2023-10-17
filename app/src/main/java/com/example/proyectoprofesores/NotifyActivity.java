package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotifyActivity extends AppCompatActivity {
    ArrayList<Notificaciones> listNotify;
    RecyclerView recyc;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
    }
}
