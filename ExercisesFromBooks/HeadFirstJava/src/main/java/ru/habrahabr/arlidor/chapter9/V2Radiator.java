package ru.habrahabr.arlidor.chapter9;

import java.util.List;

class V2Radiator {

    V2Radiator(List list) {
        System.out.println("making a v2 radiator");
        for (int x = 0; x < 5; x++) {
            list.add(new SimUnit("V2Radiator"));
        }
    }
}
