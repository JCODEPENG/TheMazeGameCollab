package com.company;

public class Cheese {
    private int PositionX;
    private int PositionY;
    private int PlayerSymbol;

    public Cheese(int PosX, int PosY, int Symbol){
        this.PositionX = PosX;
        this.PositionY = PosY;
        this.PlayerSymbol = Symbol;
    }

    public int GetX(){
        return this.PositionX;
    }

    public int GetY(){
        return this.PositionY;
    }

    public int GetSymbol(){
        return this.PlayerSymbol;
    }
}
