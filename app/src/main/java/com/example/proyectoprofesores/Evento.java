package com.example.proyectoprofesores;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Evento {

    int idHorario;

    String curso;
    String aula;
    String nivel;
    String dia;
    Time horaInicio;
    Time horaFin;

    public Evento(int idHorario, String curso, String aula, String nivel, String dia, Time horaInicio, Time horaFin) {
        this.idHorario = idHorario;
        this.curso = curso;
        this.aula = aula;
        this.nivel = nivel;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getAula() {
        return "Salón " + aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getNivel() {
        switch (this.nivel){
            case "PRIM":
                return "NIVEL PRIMARIA";
            case "SEC":
                return "NIVEL SECUNDARIA";
            default:
                return "NIVEL NO ENCONTRADO";
        }

    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }
    public static String convertirTiempoAstring(Time hora){
        Time horaF = hora;
        // Formatear la hora como una cadena
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horaFormateada = sdf.format(hora);

        return horaFormateada;
    }

    public static void ordenarListaPorHoraInicio(List<Evento> listaDeHorarios) {
        Collections.sort(listaDeHorarios, Comparator.comparing(horario -> horario.horaInicio));
    }
    public static Time obtenerHoraActual() {
        return new Time(System.currentTimeMillis());
    }
    private static Date obtenerFechaActual() {
        return new Date();
    }

    public static ArrayList<Evento> filtrarPorDia(ArrayList<Evento> listaDeHorarios, String dia) {
        ArrayList<Evento> horariosFiltrados = new ArrayList<>();
        for (Evento horario : listaDeHorarios) {
            if (horario.dia.equalsIgnoreCase(dia)) {
                horariosFiltrados.add(horario);
            }
        }
        return horariosFiltrados;
    }

    public static List<Evento> filtrarEventosPasados(List<Evento> listaDeEventos) {
        List<Evento> eventosFiltrados = new ArrayList<>();
        Date ahora = obtenerFechaActual();

        for (Evento evento : listaDeEventos) {
            Date fechaHoraInicio = combinarFechaYHora(ahora, evento.horaInicio);

            if (!fechaHoraInicio.before(ahora)) {
                eventosFiltrados.add(evento);
            }
        }

        return eventosFiltrados;
    }

    // Función para combinar fecha y hora
    private static Date combinarFechaYHora(Date fecha, Time hora) {
        Calendar calFecha = Calendar.getInstance();
        calFecha.setTime(fecha);

        Calendar calFechaHora = Calendar.getInstance();
        calFechaHora.set(calFecha.get(Calendar.YEAR), calFecha.get(Calendar.MONTH), calFecha.get(Calendar.DAY_OF_MONTH),
                hora.getHours(), hora.getMinutes(), hora.getSeconds());

        return calFechaHora.getTime();
    }

}
