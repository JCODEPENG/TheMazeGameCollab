package com.company;

import java.awt.*;
import java.util.Random;

public class Cat extends Mouse {
    private static int lastX;
    private static int lastY;
    private static boolean didBackTrace = false;

    public Cat(int x, int y, int symbol) {
        super(x, y, symbol);
    }

    public boolean didBackTrace(){
        return didBackTrace;
    }
    public void setBackTrace(boolean didBackTrace){
        this.didBackTrace = didBackTrace;
    }

    public int getLastX(){
        return lastX;
    }
    public void setLastX(int lastX){
        this.lastX = lastX;
    }
    public int getLastY(){
        return lastY;
    }
    public void setLastY(int lastY){
        this.lastY = lastY;
    }

}
