package com.company;

import java.util.Random;

public class MazeHandler {
    private int[][] BaseMaze;
    private int UnexploredRegions[][];
    private Mouse Player = new Mouse(1 ,1 , 4);
    private Cat Cat1 = new Cat(18, 4, 5);
    private Cheese Cheese;


    public MazeHandler(){
        TemporaryMaze();
    }

    public void UpdatePlayer(int Choice){
        int PreviousPositionX = this.Player.GetX();
        int PreviousPositionY = this.Player.GetY();

        if (Choice == 1) {
            this.Player.MoveUp();
            if (isWall(this.Player.GetX(), this.Player.GetY())) {
                this.Player.MoveDown();
                System.out.println("Error it's a wall");
            } else {
                this.BaseMaze[PreviousPositionY][PreviousPositionX] = 2;
                this.BaseMaze[this.Player.GetY()][this.Player.GetX()] = this.Player.GetSymbol();
            }
        }
        if(Choice == 2) {
            this.Player.MoveLeft();
            if (isWall(this.Player.GetX(), this.Player.GetY())) {
                this.Player.MoveRight();
                System.out.println("Error it's a wall");
            } else {
                this.BaseMaze[PreviousPositionY][PreviousPositionX] = 2;
                this.BaseMaze[this.Player.GetY()][this.Player.GetX()] = this.Player.GetSymbol();
            }
        }
        if(Choice == 3) {
            this.Player.MoveDown();
            if (isWall(this.Player.GetX(), this.Player.GetY())) {
                this.Player.MoveUp();
                System.out.println("Error it's a wall");
            } else {
                this.BaseMaze[PreviousPositionY][PreviousPositionX] = 2;
                this.BaseMaze[this.Player.GetY()][this.Player.GetX()] = this.Player.GetSymbol();
            }
        }
        if (Choice == 4) {
            this.Player.MoveRight();
            if (isWall(this.Player.GetX(), this.Player.GetY())) {
                this.Player.MoveLeft();
                System.out.println("Error it's a wall");
            } else {
                this.BaseMaze[PreviousPositionY][PreviousPositionX] = 2;
                this.BaseMaze[this.Player.GetY()][this.Player.GetX()] = this.Player.GetSymbol();
            }
        }

    }


    public void UpdateCat(){
        boolean CatMoved = false;
        int PreviousPositionX = Cat1.GetX();
        int PreviousPositionY = Cat1.GetY();
        while(!CatMoved) {

            Cat1.MoveRandom();
            if (isWall(Cat1.GetX(), Cat1.GetY())) {
                Cat1.SetX(PreviousPositionX);
                Cat1.SetY(PreviousPositionY);

            }
            else {
                this.BaseMaze[Cat1.GetY()][Cat1.GetX()] = Cat1.GetSymbol();
                this.BaseMaze[PreviousPositionY][PreviousPositionX] = 2;
                CatMoved = true;
            }
        }
    }

    public void UpdateCheese(){
        Random Choice1 = new Random();
        Random Choice2 = new Random();
        boolean CarryOn = true;
        while (CarryOn){
            int RandomXCoord = Choice1.nextInt(15);
            int RandomYCoord = Choice2.nextInt(6);
            if (BaseMaze[RandomYCoord][RandomXCoord] == Player.GetSymbol() || isWall(RandomXCoord,RandomYCoord)){
                CarryOn = true;
            }
            else{
                Cheese = new Cheese(RandomXCoord,RandomYCoord, 6);
                BaseMaze[RandomYCoord][RandomXCoord] = Cheese.GetSymbol();
                CarryOn = false;
            }
        }

    }

    if

    public boolean isWall(int PosX, int PosY){
        if (this.BaseMaze[PosY][PosX] == 3){
            return true;
        }
        else{
            return false;
        }
    }

    public void TemporaryMaze(){
        this.BaseMaze = new int[][]{
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {3,2,2,2,3,2,2,2,3,2,2,3,3,2,2,3,2,2,2,3},
                {3,2,3,2,2,2,3,2,3,2,3,2,3,2,3,2,3,2,3,3},
                {3,3,3,2,3,3,3,3,3,2,3,3,2,2,3,2,2,2,2,3},
                {3,2,2,2,2,2,2,3,2,2,2,3,2,2,3,2,2,3,2,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}

        };
        this.BaseMaze[this.Player.GetY()][this.Player.GetX()] = this.Player.GetSymbol();
        this.BaseMaze[Cat1.GetY()][Cat1.GetX()] = Cat1.GetSymbol();
    }

    public void tmpPrint(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 20; j++){
                System.out.print( this.BaseMaze[i][j]);
            }
            System.out.println();
        }
    }



}
