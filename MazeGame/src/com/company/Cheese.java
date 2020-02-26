package com.company;

public class Cheese {
    private int positionX;
    private int positionY;
    private int playerSymbol;

    public Cheese(int PosX, int PosY, int Symbol){
        this.positionX = PosX;
        this.positionY = PosY;
        this.playerSymbol = Symbol;
    }

    public int getX(){
        return this.positionX;
    }

    public int getY(){
        return this.positionY;
    }

    public int getSymbol(){
        return this.playerSymbol;
    }
}
