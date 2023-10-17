package com.example.proyectoprofesores;

public class CalendarItem {
    private String dayOfWeek;  // Día de la semana (por ejemplo, "Lun")
    private String dayNumber;  // Número del día (por ejemplo, "11")
    private int year;          // Año
    private int month;         // Mes (0 para enero, 1 para febrero, ...)
    private String monthName;

    public CalendarItem(String dayOfWeek, String dayNumber, int year, int month, String monthName) {
        this.dayOfWeek = dayOfWeek;
        this.dayNumber = dayNumber;
        this.year = year;
        this.month = month;
        this.monthName = monthName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
    public static String getFullWeekName(String weekName){
        switch (weekName){
            case "Lun":
                return "Lunes";
            case "Mar":
                return "Martes";
            case "Mié":
                return "Miércoles";
            case "Jue":
                return "Jueves";
            case "Vie":
                return "Viernes";
            case "Sáb":
                return "Sábado";
            case "Dom":
                return "Domingo";
        }
        return "none";
    }
}
