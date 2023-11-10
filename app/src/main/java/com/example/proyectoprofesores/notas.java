package com.example.proyectoprofesores;

public class notas {
    private int id;
    private String title;
    private String content;
    private String fecha;
    private String hora;


    public notas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public notas(int id, String title, String content, String fecha, String hora) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.fecha = fecha;
        this.hora = hora;
    }

}
