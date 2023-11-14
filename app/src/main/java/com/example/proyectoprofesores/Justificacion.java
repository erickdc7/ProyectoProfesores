package com.example.proyectoprofesores;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Justificacion {
    private int idJustificacion;
    private String nombre;
    private String aula;
    private Date fecha;

    public Justificacion(int idJustificacion, String nombre, String aula, Date fecha) {
        this.idJustificacion = idJustificacion;
        this.nombre = nombre;
        this.aula = aula;
        this.fecha = fecha;
    }

    public int getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(int idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public static String convertirFechaAString(Date fechaSql) {
        java.util.Date utilDate = new java.util.Date(fechaSql.getTime());

        // Convertir java.util.Date a LocalDate
        LocalDate fechaLocal = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Definir un patrón de formato de fecha que desees, por ejemplo, "dd/MM/yyyy"
        String patronFecha = "dd/MM/yyyy";

        // Formatear LocalDate a String utilizando DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patronFecha);
        return fechaLocal.format(formatter);
    }
    public static String getApellidos(String nombre){
        String[] partes = nombre.split(" ");

        // Obtener los dos últimos elementos del array (apellidos)
        String apellido1 = partes[0];
        String apellido2 = partes[1];
        return apellido1 + " " + apellido2;
    }
}
