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
        int[][] BaseMaze = TwoMazes.returnBaseMaze();
        int[][] Unexplored = TwoMazes.returnUnexploredMaze();
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

    public boolean checkGameState(DisplayOutput PrintToScreen, GameHandler Organizer, MazeHandler MazeCheck){
        if (CheeseCollected == TotalCheeseNeeded){
            PrintToScreen.WinMsg();
            PrintToScreen.OutputMaze(MazeCheck.returnBaseMaze());
            PrintToScreen.CheeseCollected(CheeseCollected,TotalCheeseNeeded);
            return true;
        }
        if (MazeCheck.playerEaten()){
            PrintToScreen.gotEatenMsg();
            PrintToScreen.OutputMaze(Organizer.outputMaze);
            PrintToScreen.gameOverMsg();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GameHandler Organizer = new GameHandler();
        UserInput InputKey = new UserInput();
        DisplayOutput PrintToScreen = new DisplayOutput();
        MazeHandler Testing = new MazeHandler();
        boolean CarryOn = true;

        PrintToScreen.WelcomeMsg();
        PrintToScreen.HelpMsg();



        Testing.updateExploredRegions();


        while (CarryOn){
            Testing.updateExploredRegions();
            Organizer.CombineMaze(Testing);
            InputKey.GetInputKey();
            PrintToScreen.OutputMaze(Organizer.outputMaze);
            int Key = InputKey.ReturnInputKey();



            InterpretInput(Key, Testing);
            Testing.updateCat();
            if (Testing.cheeseEaten()){
                CheeseCollected++;
                PrintToScreen.CheeseCollected(CheeseCollected,TotalCheeseNeeded);
                Testing.UpdateCheese();
            }
            if (Organizer.checkGameState(PrintToScreen, Organizer, Testing)){
                break;
            }

        }

    }
}
