package com.example.proyectoprofesores;

public class Horario {
    private String idHorario;
    private String idAula;
    private String idCursos;
    private String nombreCurso = "Matematica";
    private String dia = "Lunes";
    private String horaInicio = "03:27";
    private String horafin;

    public Horario() {
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getIdCursos() {
        return idCursos;
    }

    public void setIdCursos(String idCursos) {
        this.idCursos = idCursos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }
}
