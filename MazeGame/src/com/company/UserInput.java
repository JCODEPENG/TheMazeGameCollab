package com.company;

import javax.swing.*;
import java.util.Scanner;

public class UserInput {
    private char InputKey;

    public void GetInputKey(){
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
        else if (Key == 'm'){
            this.InputKey = Key;
        }
        else if (Key == 'c'){
            this.InputKey = Key;
        }
        else{
            //PrintErrorMessage()
        }
    }

    public int ReturnInputKey(){
        if (this.InputKey == 'w'){
            return 1;
        }
        if(this.InputKey == 'a'){
            return 2;
        }
        if(this.InputKey == 's'){
            return 3;
        }
        if ((this.InputKey == 'd')){
            return 4;
        }
        if (this.InputKey == 'm'){
            return 5;
        }
        if (this.InputKey == 'c'){
            return 6;
        }
        return -1;
    }
}
