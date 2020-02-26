package com.company;

public class Mouse {
    private int positionX;
    private int positionY;
    private int playerSymbol;

    public Mouse(int posX, int posY, int symbol){
        this.positionX = posX;
        this.positionY = posY;
        this.playerSymbol = symbol;
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

    public void move(int newX, int newY){
        positionX = newX;
        positionY = newY;
    }
}
