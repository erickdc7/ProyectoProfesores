package com.example.proyectoprofesores;

import com.android.volley.VolleyError;

import org.json.JSONArray;

public interface OnAgendaClickListener {
void onErrorResponse(VolleyError error);

    void onResponse(JSONArray response);
    void onAgendaClick(int position);
}
