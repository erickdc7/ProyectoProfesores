package com.example.proyectoprofesores;

public class Notificaciones {

    public int image;
    public String titulo;
    public String noti;

   public static  class Builder {
       private Notificaciones notificaciones;

       public Builder withTitulo(String titulo){
           notificaciones.titulo=titulo;
           return this;
       }
       public Builder withNoti(String noti){
           notificaciones.noti=noti;
           return this;
       }

       public Builder withImage(int image) {
           notificaciones.image = image;
           return this;
       }

       public Notificaciones build() {
            return notificaciones;
       }
   }







}