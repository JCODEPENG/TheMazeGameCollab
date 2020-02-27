package UserInterface;

import java.util.Scanner;

/**
 * User Input class handles user's input from keyboard
 * the input keys are different from the UI class and the Game Logic class
 * to make modification easier. Keys are set to final because there is no
 * modification in this assignment
 */
public class UserInput {
    private char inputKey;
    private final char UP = 'w';
    private final char LEFT = 'a';
    private final char DOWN = 's';
    private final char RIGHT = 'd';
    private final char REVEAL_MAP = 'm';
    private final char ONE_CHEESE = 'c';
    private final char HELP = '?';


    public void getInputKey(){
        Scanner input = new Scanner(System.in);
        char key = input.next().charAt(0);
        char lowerCaseKey = Character.toLowerCase(key);
        this.inputKey = lowerCaseKey;
    }

    public int returnInputKey(){
        if (this.inputKey == UP){
            return 1;
        }
        if (this.inputKey == LEFT){
            return 2;
        }
        if (this.inputKey == DOWN){
            return 3;
        }
        if (this.inputKey == RIGHT){
            return 4;
        }
        if (this.inputKey == REVEAL_MAP){
            return 5;
        }
        if (this.inputKey == ONE_CHEESE){
            return 6;
        }
        if (this.inputKey == HELP){
            return 7;
        }
        return -1;
    }
}
