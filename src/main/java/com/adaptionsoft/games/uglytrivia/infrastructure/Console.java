package com.adaptionsoft.games.uglytrivia.infrastructure;

import com.adaptionsoft.games.uglytrivia.domain.Printer;

public class Console implements Printer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void print(Object object) {
        System.out.println(object);
    }
}
