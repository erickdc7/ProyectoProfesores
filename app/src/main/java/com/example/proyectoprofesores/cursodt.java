package com.example.proyectoprofesores;
import java.util.ArrayList;
import java.util.HashMap;

public class cursodt {
    private  int id;
    private int fondo;
    private int icon;
    private String nombre;
    private String nivel;
    private int id_aula;
    private String aula;

    private String cantAlum;

    //private ArrayList<String> dias;

    private HashMap<String, ArrayList<String>> diasPorCurso;

    public cursodt(){

    }

    public cursodt(int id, int fondo, int icon, String nombre, String nivel, int id_aula, String aula, String cantAlum, ArrayList<String> dias) {
        this.id = id;
        this.fondo = fondo;
        this.icon = icon;
        this.nombre = nombre;
        this.nivel = nivel;
        this.id_aula = id_aula;
        this.aula = aula;
        this.cantAlum = cantAlum;
        //this.dias = dias;
        this.diasPorCurso = new HashMap<>();
        this.diasPorCurso.put(nombre, dias);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFondo() {
        return fondo;
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getCantAlum() {
        return cantAlum;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public void setCantAlum(String cantAlum) {
        this.cantAlum = cantAlum;
    }

//    public ArrayList<String> getDias() {
//        return dias;
//    }

//    public void setDias(ArrayList<String> dias) {
//        this.dias = dias;
//    }
public HashMap<String, ArrayList<String>> getDiasPorCurso() {
    return diasPorCurso;
}
    public void setDiasPorCurso(HashMap<String, ArrayList<String>> diasPorCurso) {
        this.diasPorCurso = diasPorCurso;
    }
}
