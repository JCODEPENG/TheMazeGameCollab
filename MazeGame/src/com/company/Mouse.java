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



    public int GetX(){
        return this.positionX;
    }

    public int GetY(){
        return this.positionY;
    }

    public int GetSymbol(){
        return this.playerSymbol;
    }

    public void SetX(int NewXPos) {
        this.positionX = NewXPos;
    }
    public void SetY(int NewYPos){
        this.positionY = NewYPos;
    }

    public void MoveUp(){
        this.positionY = this.positionY -1;
    }

    public void MoveDown(){
        this.positionY = this.positionY + 1;
    }


    public void MoveRight(){
        this.positionX = this.positionX + 1;
    }

    public void MoveLeft(){
        this.positionX = this.positionX - 1;
    }
}
