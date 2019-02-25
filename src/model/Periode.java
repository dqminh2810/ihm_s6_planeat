package model;

import java.time.LocalDate;

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
            endDate = startDate.plusDays(1);
        }
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        if (startDate != null && startDate.isAfter(this.endDate)){
            startDate = endDate.minusDays(1);
        }
    }
}
