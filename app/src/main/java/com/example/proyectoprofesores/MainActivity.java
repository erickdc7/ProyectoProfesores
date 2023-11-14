package com.example.proyectoprofesores;

import static com.example.proyectoprofesores.AlarmNotification.NOTIFICATION_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.proyectoprofesores.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    public static final String MY_CHANNEL_ID = "myChannel";

    ActivityMainBinding binding;
    private int selectedItem = R.id.inicio;
    private View lineaSeleccion;
    private View itemSeleccionado;
    private static final int PERMISSION_REQUEST_CODE = 123;
    String idUsuario;
    String idDocente;
    String nombre;
    String apellido;
    String correo;
// 5 minutos (ajusta según tus necesidades)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lineaSeleccion = findViewById(R.id.lineaSeleccion);
        binding.bottomNavigationView.findViewById(selectedItem);
        actualizarPosicionLinea();
        Intent intent = getIntent();
        if (intent != null) {
            idUsuario = intent.getStringExtra("idUsuario");
            idDocente = intent.getStringExtra("idDocente");
            nombre = intent.getStringExtra("nombre");
            apellido = intent.getStringExtra("apellido");
            correo = intent.getStringExtra("correo");
        }

        Bundle bundle = new Bundle();
        bundle.putString("idUsuario", idUsuario);
        bundle.putString("idDocente", idDocente);
        bundle.putString("nombre", nombre);
        bundle.putString("apellido", apellido);
        bundle.putString("correo", correo);
        replaceFragment(new InicioFragment(), bundle);


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            selectedItem = item.getItemId();
            actualizarPosicionLinea();
            switch (item.getItemId()) {
                case R.id.inicio:
                    replaceFragment(new InicioFragment(), bundle);

                    break;
                case R.id.agenda:
                    replaceFragment(new AgendaFragment(), bundle);
                    break;
                case R.id.directory:
                    replaceFragment(new DirectorioFragment(), bundle);
                    break;
                case R.id.course:
                    replaceFragment(new CoursesFragment(), bundle);
                    break;
            }
            return true;
        });
        createChannel();
        scheduleNotification();
    }



    private void scheduleNotification() {
        Intent intent = new Intent(getApplicationContext(), AlarmNotification.class);
        intent.putExtra("idUsuario", idUsuario);
        intent.putExtra("idDocente", idDocente);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("correo", correo);
        Log.d("NotifyDocente", "idUsuario: " + idUsuario+"idDocente: " + idDocente);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                    Calendar.getInstance().getTimeInMillis() + 15000, pendingIntent);
        }
    }


    private void replaceFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void actualizarPosicionLinea() {
        lineaSeleccion = findViewById(R.id.lineaSeleccion);
        itemSeleccionado = binding.bottomNavigationView.findViewById(selectedItem);
        if (lineaSeleccion != null && itemSeleccionado != null) {
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

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MySuperChannel";
            String description = "SUSCRIBETE";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

}


