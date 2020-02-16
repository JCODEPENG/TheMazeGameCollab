package com.company;

import java.awt.*;
import java.util.Random;

public class Cat extends Mouse {
    public Cat(int i, int i1, int i2) {
        super(i, i1, i2);
    }

    public void MoveRandom(){
        Random choice = new Random();
        int ChoicePicked = choice.nextInt(3);
        switch(ChoicePicked){
            case 0:
                this.MoveUp();
                break;
            case 1:
                this.MoveDown();
                break;
            case 2:
                this.MoveLeft();
                break;
            case 3:
                this.MoveRight();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + ChoicePicked);
        }
    }
}
