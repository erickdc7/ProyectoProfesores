package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterNotificaciones extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_1 = 1;
    private static final int VIEW_TYPE_2 = 2;
    private static final int VIEW_TYPE_3 = 3;

    ArrayList<Notificaciones> listNotificaciones;
    Context context;

    public AdapterNotificaciones(ArrayList<Notificaciones> listNotificaciones, Context context) {
        this.listNotificaciones = listNotificaciones;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case VIEW_TYPE_1:
                view = LayoutInflater.from(context).inflate(R.layout.listview_notificaciones, parent, false);
                return new ViewHolderDatos1(view);
            case VIEW_TYPE_2:
                view = LayoutInflater.from(context).inflate(R.layout.listview_notificaciones_2, parent, false);
                return new ViewHolderDatos2(view);
            case VIEW_TYPE_3:
            default:
                view = LayoutInflater.from(context).inflate(R.layout.listview_notificaciones_3, parent, false);
                return new ViewHolderDatos3(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_1:
                ViewHolderDatos1 holder1 = (ViewHolderDatos1) holder;
                Glide.with(context).load(listNotificaciones.get(position).image).into(holder1.imgNotificaciones);
                holder1.TituloNotificacion.setText(listNotificaciones.get(position).titulo);
                holder1.Descripcion.setText(listNotificaciones.get(position).noti);
                break;
            case VIEW_TYPE_2:
                ViewHolderDatos2 holder2 = (ViewHolderDatos2) holder;
                Glide.with(context).load(listNotificaciones.get(position).image).into(holder2.imgNotificaciones);
                holder2.TituloNotificacion.setText(listNotificaciones.get(position).titulo);
                holder2.Descripcion.setText(listNotificaciones.get(position).noti);
                break;
            case VIEW_TYPE_3:
            default:
                ViewHolderDatos3 holder3 = (ViewHolderDatos3) holder;
                holder3.TituloNotificacion.setText(listNotificaciones.get(position).titulo);
                holder3.Descripcion.setText(listNotificaciones.get(position).noti);
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
            return VIEW_TYPE_1;
        } else if (position % 3 == 1) {
            return VIEW_TYPE_2;
        } else {
            return VIEW_TYPE_3;
        }
    }

    public class ViewHolderDatos1 extends RecyclerView.ViewHolder {
        ImageView imgNotificaciones;
        TextView TituloNotificacion, Descripcion;

        public ViewHolderDatos1(@NonNull View itemView) {
            super(itemView);
            imgNotificaciones = itemView.findViewById(R.id.imageViewNotificacion1);
            TituloNotificacion = itemView.findViewById(R.id.textViewTituloNotificacion1);
            Descripcion = itemView.findViewById(R.id.textViewDescripcion1);
        }
    }

    public class ViewHolderDatos2 extends RecyclerView.ViewHolder {
        ImageView imgNotificaciones;
        TextView TituloNotificacion, Descripcion;

        public ViewHolderDatos2(@NonNull View itemView) {
            super(itemView);
            imgNotificaciones = itemView.findViewById(R.id.imageViewNotificacion2);
            TituloNotificacion = itemView.findViewById(R.id.textViewTituloNotificacion2);
            Descripcion = itemView.findViewById(R.id.textViewDescripcion2);
        }
    }

    public class ViewHolderDatos3 extends RecyclerView.ViewHolder {

        TextView TituloNotificacion, Descripcion;

        public ViewHolderDatos3(@NonNull View itemView) {
            super(itemView);
            TituloNotificacion = itemView.findViewById(R.id.textViewTituloNotificacion3);
            Descripcion = itemView.findViewById(R.id.textViewDescripcion3);
        }
    }
}
