package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Contacto> lst;

    public CustomAdapter(Context context, List<Contacto> lst) {
        this.context = context;
        this.lst = lst;
    }


    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView ImageViewContacto;
        TextView TextViewNombre;
        TextView TextViewMadre;
        TextView TextViewPadre;

        Contacto c=lst.get(i);

        if(view==null)
            view= LayoutInflater.from(context).inflate(R.layout.listview_contactos,null);

        ImageViewContacto=view.findViewById(R.id.imageViewContacto);
        TextViewNombre=view.findViewById(R.id.textViewNombre);
        TextViewMadre=view.findViewById(R.id.textViewMadre);
        TextViewPadre=view.findViewById(R.id.textViewPadre);

        ImageViewContacto.setImageResource(c.image);
        TextViewNombre.setText(c.nombre);
        TextViewMadre.setText(c.num_Madre);
        TextViewPadre.setText(c.num_Padre);
        return view;
    }
}
