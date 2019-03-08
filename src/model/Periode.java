package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Periode {

    private LocalDate startDate;
    private LocalDate endDate;

    public Periode(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        if (endDate != null && endDate.isBefore(this.startDate)){
            endDate = startDate;
        }
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        if (startDate != null && startDate.isAfter(this.endDate)){
            startDate = endDate;
        }
    }

    public int getInterval(){
        return (int) startDate.until(endDate, DAYS);
    }

    public boolean containsDay(LocalDate date) {
        return ( date.isEqual(startDate) || date.isAfter(startDate) ) && ( date.isBefore(endDate) || date.isEqual(endDate) );
    }
}
