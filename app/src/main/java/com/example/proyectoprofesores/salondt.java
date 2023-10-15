package com.example.proyectoprofesores;

public class salondt {
    private int fondo;
    private String grado;
    private String seccion;

    public salondt() {
    }

    public salondt(int fondo, String grado, String seccion) {
        this.fondo = fondo;
        this.grado = grado;
        this.seccion = seccion;
    }

    public int getFondo() {
        return fondo;
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
