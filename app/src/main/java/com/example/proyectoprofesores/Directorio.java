    package com.example.proyectoprofesores;

    public class Directorio {


        public int image;

        public String nombre;
        public String num_Madre;
        public String num_Padre;
        public int  imagefondo;

       public static class Builder{

           private Directorio directorio;

           public Builder(){directorio =  new Directorio();}


           public Builder withImage(int image) {
               directorio.image = image;
               return this;
           }
           public Builder withNombre(String nombre){
               directorio.nombre=nombre;
               return this;
           }
           public Builder withNum_Madre(String num_Madre){
               directorio.num_Madre=num_Madre;
               return this;
           }

           public Builder withNum_Padre(String num_Padre){
               directorio.num_Padre=num_Padre;
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
