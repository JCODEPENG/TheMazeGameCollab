package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int cheeseCollected = 0;
    private static int totalCheeseNeeded = 5;
    private static boolean revealMaze = false;

    private int[][] outputMaze = new int[15][20];

    public static boolean InterpretInput(int Choice, MazeHandler CurrentGame){

        if (Choice == 1 || Choice == 2 || Choice == 3 || Choice == 4){
            boolean Updated = CurrentGame.updatePlayer(Choice);
            if(!Updated) {
                DisplayOutput.invalidMoveMsg();
                return false;
            }
            else{
                return true;
            }
        }
        else if (Choice == 5){
            revealMaze = true;
        }
        else if (Choice == 6){
            totalCheeseNeeded = 1;
        }
        else{
            DisplayOutput.invalidInput();
        }

        return false;

    }

    public void CombineMaze(MazeHandler TwoMazes){
        int[][] BaseMaze = TwoMazes.returnBaseMaze();
        int[][] Unexplored = TwoMazes.returnUnexploredRegion();
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 20; j++){
                if (BaseMaze[i][j] == TwoMazes.returnCatSymbol() || BaseMaze[i][j] == TwoMazes.returnPlayerSymbol() || BaseMaze[i][j] == TwoMazes.returnCheeseSymbol()
                        || Unexplored[i][j] == TwoMazes.returnExploredSymbol()) {
                    outputMaze[i][j] = BaseMaze[i][j];
                }
                else{
                    outputMaze[i][j] = Unexplored[i][j];
                }
            }
        }

    }

    public boolean checkGameState(DisplayOutput PrintToScreen, GameHandler Organizer, MazeHandler MazeCheck){
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
        GameHandler Organizer = new GameHandler();
        UserInput InputKey = new UserInput();
        DisplayOutput PrintToScreen = new DisplayOutput();
        MazeHandler Testing = new MazeHandler();
        Testing.updateCheese();
        boolean CarryOn = true;
        PrintToScreen.welcomeMsg();
        PrintToScreen.helpMsg();
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
            PrintToScreen.cheeseCollected(cheeseCollected,totalCheeseNeeded);
            PrintToScreen.getInputMsg();
            InputKey.GetInputKey();
            int Key = InputKey.ReturnInputKey();
            if(InterpretInput(Key, Testing)){
                if (Organizer.checkGameState(PrintToScreen, Organizer, Testing)){
                    break;
                }
                if (Testing.cheeseEaten()){
                    cheeseCollected++;
                    Testing.updateCheese();
                }
                Testing.updateCat();
                if (Organizer.checkGameState(PrintToScreen, Organizer, Testing)){
                    break;
                }
            }


        }

    }
}
