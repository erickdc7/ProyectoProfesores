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
 * Use the {@link DirectorioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectorioFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterDirectorio adapter;
    private ArrayList<Directorio> listDirectorio = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DirectorioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DirectorioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DirectorioFragment newInstance(String param1, String param2) {
        DirectorioFragment fragment = new DirectorioFragment();
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
        return inflater.inflate(R.layout.fragment_directorio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewDirectorio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        listDirectorio.add(new Directorio(R.drawable.boy, "Juan Pérez",
                "555 123 456", "555 789 012", R.drawable.contact_box));
        listDirectorio.add(new Directorio(R.drawable.user_image, "María González",
                "555 234 567", "555 890 123", R.drawable.contact_box_azul));
        listDirectorio.add(new Directorio(R.drawable.boy, "Pedro Rodríguez",
                "555 345 678", "555 901 234", R.drawable.contact_box));
        listDirectorio.add(new Directorio(R.drawable.user_image, "Laura Sánchez",
                "555 456 789", "555 012 345", R.drawable.contact_box_azul));
        listDirectorio.add(new Directorio(R.drawable.user_image, "María López",
                "555 111 222", "555 333 444", R.drawable.contact_box));
        listDirectorio.add(new Directorio(R.drawable.boy, "Carlos Rodríguez",
                "555 555 555", "555 666 666", R.drawable.contact_box_azul));
        listDirectorio.add(new Directorio(R.drawable.user_image, "Laura Fernández",
                "555 777 888", "555 888 999", R.drawable.contact_box));
        listDirectorio.add(new Directorio(R.drawable.boy, "Juan Martínez",
                "555 999 000", "555 000 111", R.drawable.contact_box_azul));
        listDirectorio.add(new Directorio(R.drawable.user_image, "Ana García",
                "555 222 333", "555 444 555", R.drawable.contact_box));
        listDirectorio.add(new Directorio(R.drawable.boy, "Sergio Sánchez",
                "555 666 777", "555 111 222", R.drawable.contact_box_azul));




        adapter = new AdapterDirectorio(listDirectorio);

        recyclerView.setAdapter(adapter);
    }
}