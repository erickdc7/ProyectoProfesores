package com.example.proyectoprofesores;

import java.sql.Time;

public class CursoInicio {
    String codHorario;
    String aula;
    String curso;
    String dia ;
    Time horaInicio;
    Time horaFin;
    public static class Builder{
        private CursoInicio cursoInicio;

        public Builder() {
            cursoInicio = new CursoInicio();
        }
        public CursoInicio.Builder withCodHorario(String codHorario) {
            cursoInicio.codHorario = codHorario;
            return this;
        }
        public CursoInicio.Builder withAula(String aula) {
            cursoInicio.aula = aula;
            return this;
        }
        public CursoInicio.Builder withCursos(String curso) {
            cursoInicio.curso= curso;
            return this;
        }
        public CursoInicio.Builder withDia(String dia) {
            cursoInicio.dia = dia;
            return this;
        }

        public CursoInicio.Builder withHoraInicio(Time horaInicio){
            cursoInicio.horaInicio = horaInicio;
            return this;
        }
        public CursoInicio.Builder withHoraFin(Time horaFin){
            cursoInicio.horaFin = horaFin;
            return this;
        }


        public CursoInicio build() {
            return cursoInicio;
        }
    }
}
