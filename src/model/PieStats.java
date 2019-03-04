package model;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.time.LocalDate;
import java.util.Observable;

public class PieStats {

    private ObservableList<PieChart.Data> data;
    private Periode periode;

    public PieStats ( LocalDate start, LocalDate end ){
        periode = new Periode(start, end);
        getStat();
    }

    public PieStats ( Periode periode ){
        this.periode = periode;
        getStat();
    }

    public void getStat() {
    }

    private void colorate() {

    }

}
