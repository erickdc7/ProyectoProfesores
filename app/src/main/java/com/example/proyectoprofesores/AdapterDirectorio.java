    package com.example.proyectoprofesores;


    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageSwitcher;
    import android.widget.ImageView;
    import android.widget.TextView;
    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.proyectoprofesores.Directorio;

    import java.util.ArrayList;
    import java.util.Collections;

    public class AdapterDirectorio extends RecyclerView.Adapter<AdapterDirectorio.ViewHolderDatos> {

        private ArrayList<Directorio> listDirectorio;

        public AdapterDirectorio(ArrayList<Directorio> listDirectorio) {
            this.listDirectorio = listDirectorio;
        }
        @NonNull
        @Override
        public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_contactos, parent, false);
            return new ViewHolderDatos(view);
        }


        @Override
        public void onBindViewHolder(@NonNull AdapterDirectorio.ViewHolderDatos holder, int position) {
            holder.asignarDatos(listDirectorio.get(position));



        }

        @Override
        public int getItemCount() {
            return listDirectorio.size();
        }

        public class ViewHolderDatos extends RecyclerView.ViewHolder {

            ImageView imageViewContacto;
            TextView textViewNombreAlumno;
            TextView textViewNumeroMadre;
            TextView textViewNumeroPadre;

            ImageView imageViewFondo;


            public ViewHolderDatos(@NonNull View itemView) {
                super(itemView);
                imageViewContacto = itemView.findViewById(R.id.imageViewContacto);
                textViewNombreAlumno = itemView.findViewById(R.id.textViewNombreAlumno);
                textViewNumeroMadre = itemView.findViewById(R.id.textViewNumeroMadre);
                textViewNumeroPadre = itemView.findViewById(R.id.textViewNumeroPadre);
                imageViewFondo = itemView.findViewById(R.id.imageViewFondo);
            }




            public void asignarDatos(Directorio directorio) {
                imageViewContacto.setImageResource(directorio.getImage());
                textViewNombreAlumno.setText(directorio.getNombre());
                textViewNumeroMadre.setText(directorio.getNum_Madre());
                textViewNumeroPadre.setText(directorio.getNum_Padre());
                imageViewFondo.setImageResource(directorio.getImagefondo());
            }


        }
        public void orderListByAlphabet() {
            Collections.sort(listDirectorio, (d1, d2) -> d1.getNombre().compareTo(d2.getNombre()));
        }
    }
