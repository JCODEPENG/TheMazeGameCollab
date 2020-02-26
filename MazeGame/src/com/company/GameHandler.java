package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int cheeseCollected = 0;
    private static int totalCheeseNeeded = 5;
    private static boolean revealMaze = false;

    private int[][] outputMaze = new int[15][20];

    public static void InterpretInput(int Choice, MazeHandler CurrentGame, GameHandler Organizer){

        if (Choice == 1 || Choice == 2 || Choice == 3 || Choice == 4){
            CurrentGame.updatePlayer(Choice);
        }
        if (Choice == 5){
            revealMaze = true;
        }
        if (Choice == 6){
            totalCheeseNeeded = 1;
        }
    }

    public void CombineMaze(MazeHandler TwoMazes){
        int[][] BaseMaze = TwoMazes.returnBaseMaze();
        int[][] Unexplored = TwoMazes.returnUnexploredRegion();
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 20; j++){
                if (Unexplored[i][j] == TwoMazes.returnUnexploredSymbol()) {
                    outputMaze[i][j] = Unexplored[i][j];
                }
                else{
                    outputMaze[i][j] = BaseMaze[i][j];
                }
            }
        }

    }

    public boolean checkGameState(DisplayOutput PrintToScreen, GameHandler Organizer, MazeHandler MazeCheck){
        if (cheeseCollected == totalCheeseNeeded){
            PrintToScreen.WinMsg();
            PrintToScreen.OutputMaze(MazeCheck.returnBaseMaze());
            PrintToScreen.CheeseCollected(cheeseCollected,totalCheeseNeeded);
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
        Testing.updateCheese();
        boolean CarryOn = true;
        PrintToScreen.WelcomeMsg();
        PrintToScreen.HelpMsg();
        Testing.updateExploredRegions();
        while (CarryOn){
            Testing.updateExploredRegions();
            Organizer.CombineMaze(Testing);
            if(revealMaze == false) {
                PrintToScreen.OutputMaze(Organizer.outputMaze);
            }
            else{
                PrintToScreen.OutputMaze(Testing.returnBaseMaze());
            }
            PrintToScreen.CheeseCollected(cheeseCollected,totalCheeseNeeded);
            PrintToScreen.GetInputMsg();
            InputKey.GetInputKey();
            int Key = InputKey.ReturnInputKey();
            InterpretInput(Key, Testing, Organizer);
            if (Testing.cheeseEaten()){
                cheeseCollected++;
                Testing.updateCheese();
            }
            if (Organizer.checkGameState(PrintToScreen, Organizer, Testing)){
                break;
            }

        }

    }
}
