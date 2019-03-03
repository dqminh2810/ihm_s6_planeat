package model;

import java.time.LocalDate;

public class PieStats {

    private int percentage;
    private Periode periode;

    public PieStats ( LocalDate start, LocalDate end ){
        periode = new Periode(start, end);
        getStat();
    }

    public PieStats ( Periode periode ){
        this.periode = periode;
        getStat();
    }

    private void getStat() {
    }

}
