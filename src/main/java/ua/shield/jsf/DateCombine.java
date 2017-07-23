package ua.shield.jsf;

import java.time.LocalDate;

/**
 * Created by sa on 12.06.17.
 */
public class DateCombine {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getLocaleDateOf(){
        return LocalDate.of(year,month,day);
    }

    public void setFromLocaleDate(LocalDate localDate){
        day=localDate.getDayOfMonth();
        month=localDate.getMonthValue();
        year=localDate.getYear();

    }
}
