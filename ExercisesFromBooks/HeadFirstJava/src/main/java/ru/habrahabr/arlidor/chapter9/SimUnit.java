package ru.habrahabr.arlidor.chapter9;

class SimUnit {

    private String botType;

    SimUnit(String type) {
        botType = type;
    }

    int powerUse() {
        if ("Retention".equals(botType)) {
            return 2;
        } else {
            return 4;
        }
    }
}
