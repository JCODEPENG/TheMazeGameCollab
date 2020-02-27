package com.GameLogic;

import UserInterface.*;

/**
 * Game handler is the main class that keeps the game alive
 * Game handler is responsible for cheese collected and total cheese amount
 * because it determines whether the game ends or not.
 */
public class GameHandler {
    private static int cheeseCollected = 0;
    private static int totalCheeseNeeded = 5;
    private static boolean revealMaze = false;
    private static boolean printMaze = true;
    private int[][] outputMaze = new int[15][20];

    public static boolean interpretInput(int choice, MazeHandler currentGame){
        if (choice == 1 || choice == 2 || choice == 3 || choice == 4){
            boolean Updated = currentGame.updatePlayer(choice);
            if(!Updated) {
                DisplayOutput.invalidMoveMsg();
                return false;
            } else{
                currentGame.updateCat();
                return true;
            }
        } else if (choice == 5){
            revealMaze = true;
            return true;
        } else if (choice == 6){
            totalCheeseNeeded = 1;
        } else if (choice == 7){
            DisplayOutput.helpMsg();
        } else{
            DisplayOutput.invalidInput();
        }
        return false;
    }

    public void combineMaze(MazeHandler twoMazes){
        int[][] baseMaze = twoMazes.returnBaseMaze();
        int[][] unExplored = twoMazes.returnUnexploredRegion();
        int[] xDimension = baseMaze[0];
        int xSize = xDimension.length;
        int ySize = baseMaze.length;
        for (int i = 0; i < ySize; i++){
            for (int j = 0; j < xSize; j++){
                if (baseMaze[i][j] == twoMazes.returnCatSymbol() ||
                    baseMaze[i][j] == twoMazes.returnPlayerSymbol() ||
                    baseMaze[i][j] == twoMazes.returnCheeseSymbol() ||
                    unExplored[i][j] == twoMazes.returnExploredSymbol()) {
                        outputMaze[i][j] = baseMaze[i][j];
                } else{
                    outputMaze[i][j] = unExplored[i][j];
                }
            }
        }
    }

    public boolean checkGameState(DisplayOutput printToScreen, MazeHandler mazeCheck){
        if (cheeseCollected == totalCheeseNeeded){
            printToScreen.winMsg();
            printToScreen.outputMaze(mazeCheck.returnBaseMaze());
            printToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
            return true;
        }
        if (mazeCheck.playerEaten()){
            printToScreen.gotEatenMsg();
            printToScreen.outputMaze(mazeCheck.returnBaseMaze());
            printToScreen.gameOverMsg();
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
            organizer.combineMaze(mazeHandler);
            if (printMaze) {
                if (!revealMaze) {
                    printToScreen.outputMaze(organizer.outputMaze);
                    printToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
                } else {
                    printToScreen.outputMaze(mazeHandler.returnBaseMaze());
                    printToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
                }
            }
            printToScreen.getInputMsg();
            inputKey.getInputKey();
            int Key = inputKey.returnInputKey();
            if (interpretInput(Key, mazeHandler)){
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
