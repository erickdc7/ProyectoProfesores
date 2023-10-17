package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    ArrayList<String> listaCursos;
    ArrayList<Justificacion> listaJustificaciones;
    RecyclerView recyclerCurso;
    RecyclerView recyclerJustificacion;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
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
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerCurso = view.findViewById(R.id.recyclerCourseId);
        recyclerCurso.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerCurso.setHasFixedSize(true);
        listaCursos = new ArrayList<String>();
        for(int i = 0; i<=50; i++){
            listaCursos.add("Curso #" + i);
        }
        AdapterCurso adapterc = new AdapterCurso(listaCursos);
        recyclerCurso.setAdapter(adapterc);

        recyclerJustificacion = view.findViewById(R.id.recyclerJustiId);
        recyclerJustificacion.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerJustificacion.setHasFixedSize(true);
        listaJustificaciones = new ArrayList<Justificacion>();
        for(int i = 0; i <=50; i++){
            listaJustificaciones.add(new Justificacion("nombre #" + i, "aula #" + i, "detalle #" + i));
        }
        AdapterJustificacion adapterj = new AdapterJustificacion(listaJustificaciones);
        recyclerJustificacion.setAdapter(adapterj);
    }
}