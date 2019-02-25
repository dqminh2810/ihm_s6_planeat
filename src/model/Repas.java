package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Repas {
    private ArrayList<Plat> plats;
    private LocalDateTime time;

    public Repas(ArrayList<Plat> plats, LocalDateTime time){
        this.plats = plats;
        this.time = time;
    }


}
