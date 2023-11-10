package com.example.proyectoprofesores;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDescpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDescpFragment extends Fragment implements OnNoteSavedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PREFS_NAME = "NotePrefs";
    private static final String KEY_NOTE_COUNT = "NoteCount";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String tituloN = "titulo ejemplo";
    String contentN = "conenido";
    ImageView backp;
    ArrayList<notas>  noteList = new ArrayList<>();
    Button botonInsertarNotas;
    RecyclerView recyclerView;
    NoteAdapter adapter;

    String textoCurso;
    String idUsuario;
    String idDocente;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDescpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDescpFragment newInstance(String param1, String param2) {
        CourseDescpFragment fragment = new CourseDescpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_descp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backp = view.findViewById(R.id.closeCur);
        backp.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        Bundle args = getArguments();
        idUsuario = args.getString("idUsuario", "");
        idDocente = args.getString("idDocente", "");

        if(args!=null){
            textoCurso = args.getString("curso", "");
            TextView textCour = view.findViewById(R.id.titleC);
            textCour.setText(textoCurso);
            // Usa textoSalon como desees
        }


        botonInsertarNotas = view.findViewById(R.id.botonInsertarNotas);
        botonInsertarNotas.setOnClickListener(v -> {
            NoteDetailActivity noteDetailActivity = new NoteDetailActivity();
            noteDetailActivity.setOnNoteSavedListener(this);
            Intent intent = new Intent(getContext(), NoteDetailActivity.class);
            intent.putExtra("curso", textoCurso);
            intent.putExtra("idUsuario", idUsuario);
            intent.putExtra("idDocenete", idDocente);
            startActivity(intent);
        });


        recyclerView = view.findViewById(R.id.recy_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter(getContext(), noteList);
        noteList.add(new notas(tituloN, contentN));
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        loadNotesFromPreferences();

    }
    public void saveNotesToPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_NOTE_COUNT, noteList.size());
        for(int i=0; i<noteList.size(); i++){
            notas nota = noteList.get(i);
            editor.putString("note_title_"+ i, nota.getTitle());
            editor.putString("note_content_"+ i, nota.getContent() );
        }
        editor.apply();

        Log.d("Guardado en Preferencias", "Datos guardados en preferencias compartidas.");
    }




    private void loadNotesFromPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0);
        for (int i=0; i< noteCount; i++){
            String title = sharedPreferences.getString("note_title_" + i, "");
            String content = sharedPreferences.getString("note_content_"+ i, "");
            notas nota= new notas();
            nota.setTitle(title);
            nota.setContent(content);
            noteList.add(nota);
            Log.d("Cargado desde Preferencias", "Título: " + title + ", Contenido: " + content);

        }

    }

    public void createNoteView(final notas note) {
        View noteView = getLayoutInflater().inflate(R.layout.note_item, null);
        TextView titleTextView = noteView.findViewById(R.id.titleTextView);
        TextView contentTextView = noteView.findViewById(R.id.contectTextView);
        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getContent());
        noteView.setOnLongClickListener(v -> {
            showDeleteDialog(note);
            return false;
        });
    }

    private void showDeleteDialog(final notas nota) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Eliminar esta nota.");
        builder.setMessage("¿Esta seguro de querer eliminar esta nota?");
        builder.setPositiveButton("Delete", (dialog, which) -> deleteNoteAndRefresh(nota));
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void deleteNoteAndRefresh(notas nota){
        noteList.remove(nota);
        saveNotesToPreferences();
    }


    @Override
    public void onNoteSaved(String noteTitle, String noteContent) {

        Log.d("Cargado desde noteDEs", "Título: " + noteTitle + ", Contenido: " + noteContent);
        noteList.add(new notas(noteTitle, noteContent));

    }
}