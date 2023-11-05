package com.example.proyectoprofesores;


public class JustiFaltas {
    String nomAlumno;
    String aula;
    String fechaJusti;
    String estado;
    String fInicioFalta;
    String fFinFalta;
    String descripcion;

    public static class Builder {
        private JustiFaltas justiFaltas;

        public Builder() {
            justiFaltas = new JustiFaltas();
        }

        public Builder withNomAlumno(String nomAlumno) {
            justiFaltas.nomAlumno = nomAlumno;
            return this;
        }

        public Builder withAula(String aula) {
            justiFaltas.aula = aula;
            return this;
        }

        public Builder withFechaJusti(String fechaJusti) {
            justiFaltas.fechaJusti = fechaJusti;
            return this;
        }

        public Builder withEstado(String estado) {
            justiFaltas.estado = estado;
            return this;
        }

        public Builder withFInicioFalta(String fInicioFalta) {
            justiFaltas.fInicioFalta = fInicioFalta;
            return this;
        }

        public Builder withFFinFalta(String fFinFalta) {
            justiFaltas.fFinFalta = fFinFalta;
            return this;
        }

        public Builder withDescripcion(String descripcion) {
            justiFaltas.descripcion = descripcion;
            return this;
        }

        public JustiFaltas build() {
            return justiFaltas;
        }
    }

}
