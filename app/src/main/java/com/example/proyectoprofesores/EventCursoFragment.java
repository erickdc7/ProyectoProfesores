package com.example.proyectoprofesores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventCursoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventCursoFragment extends Fragment {
    ImageView back;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String id_horario;
    private String curso;
    private String aula;
    private String dia;
    private String nivel;
    private String horaInicio;
    private String horaFin;

    public EventCursoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventCursoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventCursoFragment newInstance(String param1, String param2) {
        EventCursoFragment fragment = new EventCursoFragment();
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
        return inflater.inflate(R.layout.fragment_event_curso, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back = view.findViewById(R.id.closeEv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParentFragmentManager().popBackStack();
            }
        });
        Bundle args = getArguments();
        id_horario = args.getString("idHorario", "");
        curso = args.getString("cursos", "");
        aula = args.getString("aula", "");
        dia = args.getString("dia", "");
        nivel = args.getString("nivel", "");
        horaInicio = args.getString("horaInicio", "");
        horaFin = args.getString("horaFin", "");
        TextView textCurso = view.findViewById(R.id.nameCurso);
        textCurso.setText(curso);
        TextView textFecha = view.findViewById(R.id.fecha_curso_detail_id);
        textFecha.setText(dia);
        TextView textHora = view.findViewById(R.id.fecha_hora_detail_id);
        String hr=horaInicio + " - "+horaFin;
        textHora.setText(hr);
        TextView txtSalon = view.findViewById(R.id.salontxt);
        String sal= "Salon "+aula;
        txtSalon.setText(sal);
        TextView txtMensaje = view.findViewById(R.id.txtmensjae);
        String mensaje= "¡Recuerde marcar su entrada con el QR del salón y salir a tiempo!";
        txtMensaje.setText(mensaje);

    }
}