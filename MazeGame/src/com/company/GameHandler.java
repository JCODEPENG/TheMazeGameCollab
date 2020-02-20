package com.company;

import java.util.Scanner;

public class GameHandler {

    public static void main(String[] args) {
        MazeHandler Testing = new MazeHandler();
        boolean Carryon = true;
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
            }

        }
//        Cat Object = new Cat(1, 2, 3);
//        Object.MoveRandom();
//        int printout = Object.GetX();
//        System.out.println(printout);
    }
}
