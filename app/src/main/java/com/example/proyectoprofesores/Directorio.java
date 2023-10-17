package com.example.proyectoprofesores;

public class Directorio {


    public int image;
    public String nombre;
    public String num_Madre;
    public String num_Padre;

    public Directorio( int image, String nombre, String num_Madre, String num_Padre) {

        this.image = image;
        this.nombre = nombre;
        this.num_Madre = num_Madre;
        this.num_Padre = num_Padre;
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

    public String getNum_Madre() {
        return num_Madre;
    }



    public void setNum_Madre(String num_Madre) {
        this.num_Madre = num_Madre;
    }

    public String getNum_Padre() {
        return num_Padre;
    }

    public void setNum_Padre(String num_Padre) {
        this.num_Padre = num_Padre;
    }
}
