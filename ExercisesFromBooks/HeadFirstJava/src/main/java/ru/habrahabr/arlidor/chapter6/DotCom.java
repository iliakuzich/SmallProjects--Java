package ru.habrahabr.arlidor.chapter6;

import java.util.List;

public class DotCom {

    private String name;
    private List<String> locationCells;

    public void setLocationCells(List<String> loc) {
        locationCells = loc;
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
            } else {
                result = "hit";
            }
        }
        return result;
    }

    public void setName(String string) {
        name = string;
    }
}
