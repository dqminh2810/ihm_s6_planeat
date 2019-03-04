package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EatingHistory {

    private LocalDate startingDate;
    private Map<LocalDate, List<Ingredient>> history;

    public EatingHistory() {
        startingDate = LocalDate.now();
        history = new HashMap<>();
        history.put(startingDate, new ArrayList<>());
    }
}
