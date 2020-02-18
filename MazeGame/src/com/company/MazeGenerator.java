package com.company;

import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    private int Height = 13;
    private int Width = 18;
    private int Maze[][] = new int[15][20];
    private int WallSymbol = 3;
    private int PathSymbol = 4;
    private Stack<Integer> VisitedXCoords = new Stack();
    private Stack<Integer> VisitedYCoords = new Stack();

    //private GetNext()

    public MazeGenerator(){

        this.MazeFill();
        Random choice = new Random();
        int StartingPointX = choice.nextInt(Width);
        int StartingPointY = choice.nextInt(Height);
        Maze[StartingPointY][StartingPointX] = PathSymbol;
        VisitedXCoords.push(StartingPointX);
        VisitedYCoords.push(StartingPointY);
        while (!VisitedYCoords.isEmpty() && !VisitedXCoords.isEmpty()){
            int CurrentXCoord = VisitedXCoords.pop();
            int CurrentYCoord = VisitedYCoords.pop();
            if (CurrentXCoord + 2 <= Width && this.Maze[CurrentYCoord][CurrentXCoord+2] == WallSymbol){
                this.Maze[CurrentYCoord][CurrentXCoord+1] = PathSymbol;
                this.Maze[CurrentYCoord][CurrentXCoord+2] = PathSymbol;
                VisitedXCoords.push(CurrentXCoord + 2);
                VisitedYCoords.push(CurrentYCoord);
            }
            else if (CurrentXCoord - 2 > 0 && this.Maze[CurrentYCoord][CurrentXCoord-2] == WallSymbol){
                this.Maze[CurrentYCoord][CurrentXCoord-1] = PathSymbol;
                this.Maze[CurrentYCoord][CurrentXCoord-2] = PathSymbol;
                VisitedXCoords.push(CurrentXCoord - 2);
                VisitedYCoords.push(CurrentYCoord);
            }
            else if (CurrentYCoord + 2 <= Height && this.Maze[CurrentYCoord+2][CurrentXCoord] == WallSymbol){
                this.Maze[CurrentYCoord+1][CurrentXCoord] = PathSymbol;
                this.Maze[CurrentYCoord+2][CurrentXCoord] = PathSymbol;
                VisitedXCoords.push(CurrentXCoord);
                VisitedYCoords.push(CurrentYCoord + 2);
            }
            else if (CurrentYCoord - 2 > 0 && this.Maze[CurrentYCoord-2][CurrentXCoord] == WallSymbol){
                this.Maze[CurrentYCoord-1][CurrentXCoord] = PathSymbol;
                this.Maze[CurrentYCoord-2][CurrentXCoord] = PathSymbol;
                VisitedXCoords.push(CurrentXCoord);
                VisitedYCoords.push(CurrentYCoord - 2);
            }


        }



    }



    public void MazeFill(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 20; j++) {
                this.Maze[i][j] = WallSymbol;
                this.Maze[i][j] = WallSymbol;

            }
        }
    }
    public void OutputMaze(){
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 20; j++){
                System.out.print(this.Maze[i][j]);
            }
            System.out.println();
        }
    }

    public int[][] ReturnMaze(){
        return this.Maze;
    }



}
