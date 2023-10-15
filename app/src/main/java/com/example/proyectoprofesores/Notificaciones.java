package com.example.proyectoprofesores;

public class Notificaciones {

    public Notificaciones(int id, int image, String titulo, String noti) {
        this.id = id;
        this.image = image;
        this.titulo = titulo;
        this.noti = noti;
    }

    public int id;
    public int image;
    public String titulo;
    public String noti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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