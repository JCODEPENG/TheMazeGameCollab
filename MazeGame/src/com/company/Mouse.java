package com.company;

public class Mouse {
    private int PositionX;
    private int PositionY;
    private int PlayerSymbol;

    public Mouse(int PosX, int PosY, int Symbol){
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

    public void MoveUp(){
        this.PositionY = this.PositionY -1;
        System.out.println("postiony is now "+ this.PositionY );
    }

    public void MoveDown(){
        this.PositionY = this.PositionY + 1;
        System.out.println("postiony is now "+  this.PositionY );
    }


    public void MoveRight(){
        this.PositionX = this.PositionX + 1;
        System.out.println("postionx is now "+ this.PositionX );
    }

    public void MoveLeft(){
        this.PositionX = this.PositionX - 1;
        System.out.println("postionx is now "+ this.PositionX );
    }
}
