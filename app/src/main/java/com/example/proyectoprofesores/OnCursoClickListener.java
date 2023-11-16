package com.example.proyectoprofesores;

import com.android.volley.VolleyError;

import org.json.JSONArray;

public interface OnCursoClickListener {
    void onErrorResponse(VolleyError error);

    void onResponse(JSONArray response);

    void onCursoClick(int position);

}
