package com.example.proyectoprofesores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNotificaciones extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Notificaciones> listNotificaciones;
    private static final int VISTA1 = 1;
    private static final int VISTA2 = 2;
    private static final int VISTA3 = 3;

    public AdapterNotificaciones(ArrayList<Notificaciones> listNotificaciones) {
        this.listNotificaciones = listNotificaciones;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case VISTA1:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_notificaciones, parent, false);
                return new ViewHolderDatos1(view);
            case VISTA2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_notificaciones_2, parent, false);
                return new ViewHolderDatos2(view);
            case VISTA3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_notificaciones_3, parent, false);
                return new ViewHolderDatos3(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {

            case VISTA1:
                ViewHolderDatos1 holder1 = (ViewHolderDatos1) holder;
                holder1.imageViewNotificacion1.setImageResource(R.drawable.icon_branded_frame_32);
                holder1.textViewTituloNotificacion1.setText(listNotificaciones.get(position).getTitulo());
                holder1.textViewDescripcion1.setText(listNotificaciones.get(position).getNoti());
                break;

            case VISTA2:
                ViewHolderDatos2 holder2 = (ViewHolderDatos2) holder;
                holder2.imageViewNotificacion2.setImageResource(R.drawable.emoji);
                holder2.textViewTituloNotificacion2.setText(listNotificaciones.get(position).getTitulo());
                holder2.textViewDescripcion2.setText(listNotificaciones.get(position).getNoti());
                break;
            case VISTA3:
                ViewHolderDatos3 holder3 = (ViewHolderDatos3) holder;
                holder3.textViewTituloNotificacion3.setText(listNotificaciones.get(position).getTitulo());
                holder3.textViewDescripcion3.setText(listNotificaciones.get(position).getNoti());
                break;

        }
    }

    @Override
    public int getItemCount() {
        return listNotificaciones.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return VISTA1;
        } else if (position % 3 == 1) {
            return VISTA2;
        } else {
            return VISTA3;
        }
    }

    public class ViewHolderDatos1 extends RecyclerView.ViewHolder {
        ImageView imageViewNotificacion1;
        TextView textViewTituloNotificacion1;
        TextView textViewDescripcion1;

        public ViewHolderDatos1(@NonNull View itemView) {
            super(itemView);
            imageViewNotificacion1 = itemView.findViewById(R.id.imageViewNotificacion1);
            textViewTituloNotificacion1 = itemView.findViewById(R.id.textViewTituloNotificacion1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
        }
    }

    public class ViewHolderDatos2 extends RecyclerView.ViewHolder {
        ImageView imageViewNotificacion2;
        TextView textViewTituloNotificacion2;
        TextView textViewDescripcion2;

        public ViewHolderDatos2(@NonNull View itemView) {
            super(itemView);
            imageViewNotificacion2 = itemView.findViewById(R.id.imageViewNotificacion2);
            textViewTituloNotificacion2 = itemView.findViewById(R.id.textViewTituloNotificacion2);
            textViewDescripcion2 = itemView.findViewById(R.id.textViewDescripcion2);
        }
    }

    public class ViewHolderDatos3 extends RecyclerView.ViewHolder {
        TextView textViewTituloNotificacion3;
        TextView textViewDescripcion3;

        public ViewHolderDatos3(@NonNull View itemView) {
            super(itemView);
            textViewTituloNotificacion3 = itemView.findViewById(R.id.textViewTituloNotificacion3);
            textViewDescripcion3 = itemView.findViewById(R.id.textViewDescripcion3);
        }
    }
}
