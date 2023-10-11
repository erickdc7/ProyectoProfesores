package com.example.proyectoprofesores;

public class Justificacion {
    private String nombre;
    private String aula;
    private String especificacion;

    public Justificacion(String nombre, String aula, String especificacion) {
        this.nombre = nombre;
        this.aula = aula;
        this.especificacion = especificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }
}
