package com.example.proyectoprofesores;

public class Evento {
    String horaInicio;
    String horaFin;
    String nombreCurso;
    String temaCurso;
    String lugar;

    public Evento(String horaInicio, String horaFin, String nombreCurso, String temaCurso, String lugar) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.nombreCurso = nombreCurso;
        this.temaCurso = temaCurso;
        this.lugar = lugar;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getTemaCurso() {
        return temaCurso;
    }

    public void setTemaCurso(String temaCurso) {
        this.temaCurso = temaCurso;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
