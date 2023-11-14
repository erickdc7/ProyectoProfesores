package com.example.proyectoprofesores;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlarmNotification extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        String idUsuario = intent.getStringExtra("idUsuario");
        String idDocente = intent.getStringExtra("idDocente");
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        String correo = intent.getStringExtra("correo");

        Log.d("NotifyDocente", "idUsuario: " + idUsuario+"idDocente: " + idDocente);
        createSimpleNotification(context, idUsuario, idDocente, nombre, apellido, correo);

    }

    private void createSimpleNotification(Context context, String idUsuario, String idDocente, String nombre, String apellido, String correo){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("idUsuario", idUsuario);
        intent.putExtra("idDocente", idDocente);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("correo", correo);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        int flag = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ? PendingIntent.FLAG_IMMUTABLE : 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, flag);

        Notification notification = new NotificationCompat.Builder(context, MainActivity.MY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_noti)
                .setContentTitle("My title")
                .setContentText("Esto es un ejemplo <3")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi "))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(NOTIFICATION_ID, notification);
        }

    }
}
