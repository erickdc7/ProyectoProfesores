package com.example.proyectoprofesores;

import java.sql.Time;

public class CursoInicio {

    int  idCurso;
    String curso;

    public static class Builder{
        private CursoInicio cursoInicio;

        public Builder() {
            cursoInicio = new CursoInicio();
        }

        public CursoInicio.Builder withIdCurso(String idCurso) {
            cursoInicio.idCurso= Integer.parseInt(idCurso);
            return this;
        }


        public CursoInicio.Builder withCursos(String curso) {
            cursoInicio.curso= curso;
            return this;
        }


        public CursoInicio build() {
            return cursoInicio;
        }
    }
}
