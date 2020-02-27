package com.company;

import javax.swing.*;
import java.util.Scanner;

public class UserInput {
    private char inputKey;

    public void GetInputKey(){
        Scanner input = new Scanner(System.in);
        char key = input.next().charAt(0);
        char lowerCaseKey = Character.toLowerCase(key);
        this.inputKey = lowerCaseKey;

    }

    public int ReturnInputKey(){
        if (this.inputKey == 'w'){
            return 1;
        }
        if (this.inputKey == 'a'){
            return 2;
        }
        if (this.inputKey == 's'){
            return 3;
        }
        if (this.inputKey == 'd'){
            return 4;
        }
        if (this.inputKey == 'm'){
            return 5;
        }
        if (this.inputKey == 'c'){
            return 6;
        }
        if (this.inputKey == '?'){
            return 7;
        }
        return -1;
    }
}
