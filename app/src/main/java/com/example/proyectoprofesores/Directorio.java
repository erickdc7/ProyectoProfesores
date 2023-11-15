package com.example.proyectoprofesores;

public class Directorio {


    public int image;
    public String nombreAlumno;
    public String nombrePariente;
    public String numeroPariente;
    public int  imagefondo;

    public static class Builder{

        private Directorio directorio;

        public Builder(){directorio =  new Directorio();}


        public Builder withImage(int image) {
            directorio.image = image;
            return this;
        }
        public Builder withNombreAlumno(String nombreAlumno){
            directorio.nombreAlumno=nombreAlumno;
            return this;
        }
        public Builder withNombrePariente(String nombrePariente){
            directorio.nombrePariente=nombrePariente;
            return this;
        }

        public Builder withNumeroPariente(String numeroPariente){
            directorio.numeroPariente=numeroPariente;
            return this;
        }

        public Builder withImagefondo(int imagefondo) {
            directorio.imagefondo = imagefondo;
            return this;
        }

        public Directorio build(){
            return directorio;
        }

    }
}
