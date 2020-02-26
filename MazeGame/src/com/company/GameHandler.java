package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int CheeseCollected = 0;
    private static int TotalCheeseNeeded = 5;

    private int[][] outputMaze = new int[20][15];

    public static void InterpretInput(int Choice, MazeHandler CurrentGame){

        if (Choice == 1 || Choice == 2 || Choice == 3 || Choice == 4){
            CurrentGame.UpdatePlayer(Choice);
        }
        if (Choice == 5){
            //return the maze
        }
        if (Choice == 6){
            TotalCheeseNeeded = 1;
        }
    }

    public void CombineMaze(MazeHandler TwoMazes){
        int[][] BaseMaze = TwoMazes.ReturnBaseMaze();
        int[][] Unexplored = TwoMazes.ReturnFogMaze();
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 20; j++){
                if (Unexplored[i][j] == 1) {
                    outputMaze[i][j] = Unexplored[i][j];
                }
                else{
                    outputMaze[i][j] = BaseMaze[i][j];
                }
            }
        }

    }
    public static void main(String[] args) {
        GameHandler Organizer = new GameHandler();
        UserInput InputKey = new UserInput();

        DisplayOutput PrintToScreen = new DisplayOutput();

        PrintToScreen.WelcomeMsg();
        PrintToScreen.HelpMsg();
        MazeHandler Testing = new MazeHandler();
        Organizer.CombineMaze(Testing);

        Testing.UpdateExploredRegions();
        boolean CarryOn = true;
        Testing.UpdateCheese();
        PrintToScreen.OutputMaze(Organizer.outputMaze);
        while (CarryOn){
            InputKey.GetInputKey();

            int Key = InputKey.ReturnInputKey();
            if (Key == 6){
                break;
            }
            else{

                InterpretInput(Key, Testing);
                Testing.UpdateCat();
                if (Testing.CheeseEaten()){
                    CheeseCollected++;
                    PrintToScreen.CheeseCollected(CheeseCollected,TotalCheeseNeeded);
                    Testing.UpdateCheese();
                }
                if (Testing.PlayerEaten()){
                    System.out.println("END GAME");
                    break;
                }
                Testing.UpdateExploredRegions();

            }


        }

    }
}
