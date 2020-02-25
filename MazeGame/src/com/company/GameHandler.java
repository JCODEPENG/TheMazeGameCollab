package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int CheeseCollected = 0;
    private static int TotalCheeseNeeded = 5;



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
    public static void main(String[] args) {
        UserInput InputKey = new UserInput();

        DisplayOutput PrintToScreen = new DisplayOutput();
        PrintToScreen.WelcomeMsg();
        PrintToScreen.HelpMsg();
        MazeHandler Testing = new MazeHandler();
        Testing.UpdateExploredRegions();
        boolean CarryOn = true;
        Testing.UpdateCheese();
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

//        Cat Object = new Cat(1, 2, 3);
//        Object.MoveRandom();
//        int printout = Object.GetX();
//        System.out.println(printout);
    }
}
