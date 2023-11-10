package com.example.proyectoprofesores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity{
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        button = findViewById(R.id.button_email);
        editText = findViewById(R.id.editTextEmail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editText.getText().toString().trim();
                if (user.isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.completar_espacio, Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent =new Intent(UserActivity.this, ContraActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);

                }

            }
        });
    }



}
