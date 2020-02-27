package com.GameLogic;

/**
 * Cat class extends from the Mouse class
 * as all the methods in Mouse class is needed
 * this class holds information for backtracking
 */
public class Cat extends Mouse {
    private int lastX;
    private int lastY;
    private boolean didBackTrace = false;

    public Cat(int x, int y, int symbol) {
        super(x, y, symbol);
        this.lastX = x;
        this.lastY = y;
    }

    public boolean didBackTrace(){
        return didBackTrace;
    }
    public void setBackTrace(boolean backTrace){
        this.didBackTrace = backTrace;
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
