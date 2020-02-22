package com.company;

import java.util.Scanner;

public class GameHandler {
    private static int CheeseCollected = 0;

    public static void main(String[] args) {

        MazeHandler Testing = new MazeHandler();
        boolean Carryon = true;
        Testing.UpdateCheese();
        while (Carryon){
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
                    System.out.println("Number of Cheese eaten: " + CheeseCollected);
                    Testing.UpdateCheese();
                }
                if (Testing.PlayerEaten()){
                    System.out.println("END GAME");
                    break;
                }

            }


        }

//        Cat Object = new Cat(1, 2, 3);
//        Object.MoveRandom();
//        int printout = Object.GetX();
//        System.out.println(printout);
    }
}
