package com.example.proyectoprofesores;

public class Notificaciones {

    public int image;
    public String titulo;
    public String noti;

    public Notificaciones(int image, String titulo, String noti) {
        this.image = image;
        this.titulo = titulo;
        this.noti = noti;
    }



    public void setImage(int image) {
        this.image = image;
    }

    public int getImage(int image){
        return image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

}