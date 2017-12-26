package ru.habrahabr.arlidor.chapter9;

import java.util.List;

class V3Radiator extends V2Radiator {

    V3Radiator(List list) {
        super(list);
        for (int g = 0; g < 10; g++) {
            list.add(new SimUnit("V3Radiator"));
        }
    }
}
