package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int cheeseCollected = 0;
    private static int totalCheeseNeeded = 5;
    private static boolean revealMaze = false;
    private static boolean printMaze = true;
    private int[][] outputMaze = new int[15][20];

    public static boolean InterpretInput(int Choice, MazeHandler CurrentGame){
        if (Choice == 1 || Choice == 2 || Choice == 3 || Choice == 4){
            boolean Updated = CurrentGame.updatePlayer(Choice);
            if(!Updated) {
                DisplayOutput.invalidMoveMsg();
                return false;
            }
            else{
                CurrentGame.updateCat();
                return true;
            }
        }
        else if (Choice == 5){
            revealMaze = true;
            return true;

        }
        else if (Choice == 6){
            totalCheeseNeeded = 1;
        }
        else if (Choice == 7){
            DisplayOutput.helpMsg();
        }
        else{
            DisplayOutput.invalidInput();
        }
        return false;

    }

    public void CombineMaze(MazeHandler TwoMazes){
        int[][] baseMaze = TwoMazes.returnBaseMaze();
        int[][] unExplored = TwoMazes.returnUnexploredRegion();
        int[] XDimension = baseMaze[0];
        int XSize = XDimension.length;
        int YSize = baseMaze.length;
        for (int i = 0; i < YSize; i++){
            for (int j = 0; j < XSize; j++){
                if (baseMaze[i][j] == TwoMazes.returnCatSymbol() || baseMaze[i][j] == TwoMazes.returnPlayerSymbol() || baseMaze[i][j] == TwoMazes.returnCheeseSymbol()
                        || unExplored[i][j] == TwoMazes.returnExploredSymbol()) {
                    outputMaze[i][j] = baseMaze[i][j];
                }
                else{
                    outputMaze[i][j] = unExplored[i][j];
                }
            }
        }
    }

    public boolean checkGameState(DisplayOutput PrintToScreen, MazeHandler MazeCheck){
        if (cheeseCollected == totalCheeseNeeded){
            PrintToScreen.winMsg();
            PrintToScreen.OutputMaze(MazeCheck.returnBaseMaze());
            PrintToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
            return true;
        }
        if (MazeCheck.playerEaten()){
            PrintToScreen.gotEatenMsg();
            PrintToScreen.OutputMaze(MazeCheck.returnBaseMaze());
            PrintToScreen.gameOverMsg();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GameHandler organizer = new GameHandler();
        UserInput inputKey = new UserInput();
        DisplayOutput printToScreen = new DisplayOutput();
        MazeHandler mazeHandler = new MazeHandler();
        boolean carryOn = true;
        DisplayOutput.welcomeMsg();
        DisplayOutput.helpMsg();
        mazeHandler.updateExploredRegions();
        while (carryOn){
            mazeHandler.updateExploredRegions();
            organizer.CombineMaze(mazeHandler);
            if (printMaze) {
                if (revealMaze == false) {
                    printToScreen.OutputMaze(organizer.outputMaze);
                    printToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
                } else {
                    printToScreen.OutputMaze(mazeHandler.returnBaseMaze());
                    printToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
                }
            }
            printToScreen.getInputMsg();
            inputKey.GetInputKey();
            int Key = inputKey.ReturnInputKey();
            if (InterpretInput(Key, mazeHandler)){
                if (organizer.checkGameState(printToScreen, mazeHandler)){
                    break;
                }
                if (mazeHandler.cheeseEaten()){
                    cheeseCollected++;
                }
                if (organizer.checkGameState(printToScreen, mazeHandler)){
                    break;
                }
                if (mazeHandler.cheeseEaten()){
                    mazeHandler.updateCheese();
                }
                printMaze = true;
            }
            else{
                printMaze = false;
            }
        }
    }
}
