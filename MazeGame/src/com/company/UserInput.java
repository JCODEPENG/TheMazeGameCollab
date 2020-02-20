package com.company;

import java.util.Scanner;

public class UserInput {
    private char InputKey;

    public void GetInputKey(){
        System.out.println("Input Key: ");
        Scanner Input = new Scanner(System.in);
        char Key = Input.next().charAt(0);
        if (Key == 'w'){
           this.InputKey = Key;
        }
        else if (Key == 'a'){
            this.InputKey = Key;
        }
        else if (Key == 's'){
            this.InputKey = Key;
        }
        else if(Key == 'd'){
            this.InputKey = Key;
        }
        else{
            //PrintErrorMessage()
        }
    }

    public char ReturnInputKey(){
        return this.InputKey;
    }
}
