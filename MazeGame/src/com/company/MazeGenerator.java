package com.company;

import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    private int Height = 18;
    private int Width = 13;
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
