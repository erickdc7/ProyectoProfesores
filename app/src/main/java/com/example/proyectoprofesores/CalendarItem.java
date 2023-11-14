package com.example.proyectoprofesores;

public class CalendarItem {
    private int dayOfWeekNumber;
    private String dayOfWeek;  // Día de la semana (por ejemplo, "Lun")
    private String dayNumber;  // Número del día (por ejemplo, "11")
    private int year;          // Año
    private int month;         // Mes (0 para enero, 1 para febrero, ...)
    private String monthName;
    private boolean isSelected;

    public CalendarItem(String dayOfWeek,int dayOfWeekNumber, String dayNumber, int year, int month, String monthName) {
        this.dayOfWeekNumber = dayOfWeekNumber;
        this.dayOfWeek = dayOfWeek;
        this.dayNumber = dayNumber;
        this.year = year;
        this.month = month;
        this.monthName = monthName;
        this.isSelected = false;
    }

    public int getDayOfWeekNumber() {
        return dayOfWeekNumber;
    }

    public void setDayOfWeekNumber(int dayOfWeekNumber) {
        this.dayOfWeekNumber = dayOfWeekNumber;
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
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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


    public static String obtenerNombreDiaSemanaBD(int dayOfWeek) {
        String[] nombresDias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        return nombresDias[dayOfWeek - 1];
    }
}
