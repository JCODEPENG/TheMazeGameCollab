package com.company;

import javax.swing.*;
import java.util.Scanner;

public class UserInput {
    private char inputKey;

    public void GetInputKey(){
        Scanner Input = new Scanner(System.in);
        char Key = Input.next().charAt(0);
        this.inputKey = Key;

    }

    public int ReturnInputKey(){
        if (this.inputKey == 'w' || this.inputKey == 'W'){
            return 1;
        }
        if (this.inputKey == 'a' || this.inputKey == 'A'){
            return 2;
        }
        if (this.inputKey == 's' || this.inputKey == 'S'){
            return 3;
        }
        if (this.inputKey == 'd' || this.inputKey == 'D'){
            return 4;
        }
        if (this.inputKey == 'm' || this.inputKey == 'M'){
            return 5;
        }
        if (this.inputKey == 'c' || this.inputKey == 'C'){
            return 6;
        }
        if (this.inputKey == '?'){
            return 7;
        }
        return -1;
    }
}
