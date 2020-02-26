package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int CheeseCollected = 0;

    public static void main(String[] args) {
        DisplayOutput test = new DisplayOutput();
        test.WelcomeMsg();
        test.HelpMsg();
        MazeHandler Testing = new MazeHandler();
        Testing.updateExploredRegions();
        boolean CarryOn = true;
        Testing.UpdateCheese();
        while (CarryOn){
            System.out.println("Input Key: ");
            Scanner Input = new Scanner(System.in);
            int Key = Input.nextInt();
            if (Key == 6){
                break;
            }
            else{

                Testing.UpdatePlayer(Key);
                Testing.updateCat();
                if (Testing.cheeseEaten()){

                    CheeseCollected++;
                    System.out.println("Number of Cheese eaten: " + CheeseCollected);
                    Testing.UpdateCheese();
                }
                if (Testing.playerEaten()){
                    System.out.println("END GAME");
                    break;
                }
                Testing.updateExploredRegions();

            }


        }

//        Cat Object = new Cat(1, 2, 3);
//        Object.MoveRandom();
//        int printout = Object.GetX();
//        System.out.println(printout);
    }
}
