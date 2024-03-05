package com.msaggik.seventhlessonfindfivekeys;

import java.util.Random;

public class Key {
    private int x;
    private int y;
    private String name;



    public Key(String name) {
        this.x = new Random().ints(1,0,1044).findFirst().getAsInt();
        this.y = new Random().ints(1,0,1510).findFirst().getAsInt();
        this.name = name;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
