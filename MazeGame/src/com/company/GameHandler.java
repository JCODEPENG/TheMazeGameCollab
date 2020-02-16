package com.company;

public class GameHandler {

    public static void main(String[] args) {
        Cat Object = new Cat(1, 2, 3);
        Object.MoveRandom();
        int printout = Object.GetX();
        System.out.println(printout);
    }
}
