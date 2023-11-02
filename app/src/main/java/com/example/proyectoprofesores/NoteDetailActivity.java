package com.example.proyectoprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class NoteDetailActivity extends AppCompatActivity {
    EditText titleEditText, contentEditText;
    ImageButton saveNoteBtn;
    ArrayList<notas> noteList;
    ImageView volver;

    private OnNoteSavedListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        noteList = new ArrayList<>();
        saveNoteBtn.setOnClickListener((v)-> saveNote());
        volver = findViewById(R.id.volverbt);
        volver.setOnClickListener(v -> finish());
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("curso")) {
            String curso = intent.getStringExtra("curso");
            // Usa el título del curso como desees
        }

        // Inicializa el listener aquí si es necesario
        listener = new CourseDescpFragment();
    }

    private void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if(!noteTitle.isEmpty() && !noteContent.isEmpty()){
            listener.onNoteSaved(noteTitle, noteContent);
            finish();
        }
    }

    private void clearInputFields() {
        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }
    public void setOnNoteSavedListener(OnNoteSavedListener listener) {
        this.listener = listener;
    }

}