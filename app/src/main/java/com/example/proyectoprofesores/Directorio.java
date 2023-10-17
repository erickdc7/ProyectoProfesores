package com.example.proyectoprofesores;

public class Directorio {

    public int id;
    public int image;
    public String nombre;
    public int num_Madre;
    public int num_Padre;

    public Directorio(int id, int image, String nombre, int num_Madre, int num_Padre) {
        this.id = id;
        this.image = image;
        this.nombre = nombre;
        this.num_Madre = num_Madre;
        this.num_Padre = num_Padre;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_Madre() {
        return num_Madre;
    }

    public void setNum_Madre(int num_Madre) {
        this.num_Madre = num_Madre;
    }

    public int getNum_Padre() {
        return num_Padre;
    }

    public void setNum_Padre(int num_Padre) {
        this.num_Padre = num_Padre;
    }
}
