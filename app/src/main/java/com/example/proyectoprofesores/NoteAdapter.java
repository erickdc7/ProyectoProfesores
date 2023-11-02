package com.example.proyectoprofesores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolderDatos> {

    Context context;
    ArrayList<notas> listNotas;

    OnNoteClickListener listener;

    public void setOnNoteClickListener(OnNoteClickListener listener){
        this.listener=listener;
    }

    public NoteAdapter(Context context, ArrayList<notas> listNotas) {
        this.context = context;
        this.listNotas = listNotas;
    }

    public NoteAdapter(Context context, ArrayList<notas> listNotas, OnNoteClickListener listener) {
        this.context = context;
        this.listNotas = listNotas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.titulo.setText(listNotas.get(position).getTitle());
        holder.content.setText(listNotas.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return listNotas.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo, content;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.titleTextView);
            content=itemView.findViewById(R.id.contectTextView);

        }
    }

}
