package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.proyectoprofesores.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private int selectedItem = R.id.inicio;
    private View lineaSeleccion;
    private View itemSeleccionado;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lineaSeleccion = findViewById(R.id.lineaSeleccion);
        binding.bottomNavigationView.findViewById(selectedItem);
        actualizarPosicionLinea();
        replaceFragment(new InicioFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            selectedItem = item.getItemId();
            actualizarPosicionLinea();
            switch (item.getItemId()){
                case R.id.inicio:
                    replaceFragment(new InicioFragment());
                    break;
                case R.id.agenda:
                    replaceFragment(new AgendaFragment());
                    break;
                case R.id.directory:
                    replaceFragment(new DirectorioFragment());
                    break;
                case R.id.course:
                    replaceFragment(new SalonFragment());
                    break;
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void actualizarPosicionLinea(){
        lineaSeleccion = findViewById(R.id.lineaSeleccion);
        itemSeleccionado = binding.bottomNavigationView.findViewById(selectedItem);
        if(lineaSeleccion!= null && itemSeleccionado !=null) {
        int left = itemSeleccionado.getLeft();
        int right = itemSeleccionado.getRight();
        int width = right - left - 80;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) lineaSeleccion.getLayoutParams();
            params.leftMargin = left + 40;
            params.rightMargin = right + 40;
            params.width = width;
            lineaSeleccion.setLayoutParams(params);
        }
    }


}