package com.example.proyectoprofesores;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScanActivity extends AppCompatActivity {
    Button scan_btn;
    TextView textView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistenciaqr);
        scan_btn = findViewById(R.id.btn_scan);
        textView = findViewById(R.id.text1);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(ScanActivity.this);
                integrator.setOrientationLocked(true);
                integrator.setPrompt("Escanea el código QR");
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String contents = result.getContents();
            if (contents == null) {
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_LONG).show();
            } else {
                String scannedText = result.getContents();
                verificarAsistencia(scannedText);
                textView.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void verificarAsistencia(String codigoQR) {

        Toast.makeText(this, "Código QR inválido o fuera del horario", Toast.LENGTH_LONG).show();
    }

}
