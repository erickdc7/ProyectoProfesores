package com.example.proyectoprofesores;
import java.util.ArrayList;
public class cursodt {
    private int fondo;
    private int icon;
    private String nombre;
    private String nivel;
    private String grado;
    private String seccion;

    private String cantAlum;

    private ArrayList<String> dias;


    public cursodt(){

    }

    public cursodt(int fondo, int icon, String nombre, String nivel, String grado, String seccion, String cantAlum, ArrayList<String> dias) {
        this.fondo = fondo;
        this.icon = icon;
        this.nombre = nombre;
        this.nivel = nivel;
        this.grado = grado;
        this.seccion = seccion;
        this.cantAlum = cantAlum;
        this.dias = dias;
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCantAlum() {
        return cantAlum;
    }

    public void setCantAlum(String cantAlum) {
        this.cantAlum = cantAlum;
    }

    public ArrayList<String> getDias() {
        return dias;
    }

    public void setDias(ArrayList<String> dias) {
        this.dias = dias;
    }
}
