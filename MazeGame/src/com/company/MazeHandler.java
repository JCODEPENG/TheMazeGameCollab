package com.company;

public class MazeHandler {
    private int[][] BaseMaze;
    private int UnexploredRegions[][];
    private Mouse Player = new Mouse(1 ,1 , 4);

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
                {3,3,3,3,3,3,3,3,3},
                {3,2,2,2,3,2,2,2,3},
                {3,2,3,2,2,2,3,2,3},
                {3,3,3,3,3,3,3,3,3}
        };
        this.BaseMaze[this.Player.GetY()][this.Player.GetX()] = this.Player.GetSymbol();
    }
    public void tmpPrint(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 9; j++){
                System.out.print( this.BaseMaze[i][j]);
            }
            System.out.println();
        }
    }



}
