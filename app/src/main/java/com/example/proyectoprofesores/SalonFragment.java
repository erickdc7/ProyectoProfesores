package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalonFragment extends Fragment implements OnSalonClickListener{
    ArrayList<salondt> listSalon;


    RecyclerView recy;
    RecyclerView.LayoutManager layoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SalonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalonFragment newInstance(String param1, String param2) {
        SalonFragment fragment = new SalonFragment();
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
        View view = inflater.inflate(R.layout.fragment_salon, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy = view.findViewById(R.id.recyclersalon);
        layoutManager = new GridLayoutManager(getContext(), 2 );
        recy.setLayoutManager(layoutManager);
        listSalon = new ArrayList<>();
        llenarSalon();
        adapterSalon adaptersalon = new adapterSalon(getContext(), listSalon);
        adaptersalon.setOnSalonItemClickListener(this);
        recy.setAdapter(adaptersalon);
        recy.setHasFixedSize(true);

    }

    private void llenarSalon() {
        listSalon.add(new salondt(R.drawable.fondo_salon, "1", "A"));
        listSalon.add(new salondt(R.drawable.fondo_salon2, "1", "B"));
        listSalon.add(new salondt(R.drawable.fondo_salon2, "2", "A"));
        listSalon.add(new salondt(R.drawable.fondo_salon, "2", "B"));
        listSalon.add(new salondt(R.drawable.fondo_salon, "3", "A"));
        listSalon.add(new salondt(R.drawable.fondo_salon2, "3", "B"));
        listSalon.add(new salondt(R.drawable.fondo_salon2, "4", "A"));
        listSalon.add(new salondt(R.drawable.fondo_salon, "4", "B"));
    }


    @Override
    public void onSalonClick(int position) {
        String textoSalon = listSalon.get(position).getGrado() + listSalon.get(position).getSeccion();

        CoursesFragment fragment = new CoursesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("textoSalon", textoSalon);
        fragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}