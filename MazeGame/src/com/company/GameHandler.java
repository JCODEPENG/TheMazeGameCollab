package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int CheeseCollected = 0;
    private static int TotalCheeseNeeded = 5;

    public void InterpretInput(int )
    public static void main(String[] args) {
        DisplayOutput PrintToScreen = new DisplayOutput();
        PrintToScreen.WelcomeMsg();
        PrintToScreen.HelpMsg();
        MazeHandler Testing = new MazeHandler();
        Testing.UpdateExploredRegions();
        boolean CarryOn = true;
        Testing.UpdateCheese();
        while (CarryOn){
            Testing.tmpPrint();
            System.out.println("Input Key: ");
            Scanner Input = new Scanner(System.in);
            int Key = Input.nextInt();
            if (Key == 6){
                break;
            }
            else{

                Testing.UpdatePlayer(Key);
                Testing.UpdateCat();
                if (Testing.CheeseEaten()){
                    CheeseCollected++;
                    PrintToScreen.CheeseCollected();
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
