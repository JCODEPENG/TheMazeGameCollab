package com.company;

public class DisplayOutput {
    public void OutputMaze(int[][] Maze){
        System.out.println("Maze: ");
        assert(Maze != null);
        int[] XDimension = Maze[0];
        int XSize = XDimension.length;
        int YSize = Maze.length;
        for  (int i = 0; i < YSize; i++){
            for (int j = 0; j < XSize; j++){
                if (Maze[i][j] == 0){
                    System.out.print('#');
                }
                if (Maze[i][j] == 1){
                    System.out.print(' ');
                }
                if (Maze[i][j] == 3){
                    System.out.print('.');
                }
                if(Maze[i][j] == 4){
                    System.out.print('@');
                }
                if (Maze[i][j] == 5){
                    System.out.print('!');
                }
                if (Maze[i][j] == 6){
                    System.out.print('$');
                }
                if (Maze[i][j] == 7){
                    System.out.print('X');
                }

            }
            System.out.println();
        }


    }

    public void welcomeMsg(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Cat and Mouse Maze Adventure!\nBy Joshua Peng and Jocelyn Gau");
        System.out.println("----------------------------------------");
    }

    public void helpMsg(){
        System.out.println("Directions:\n\tFind 5 cheese before a cat eats you!");
        System.out.println("Legend:\n\t#: Wall\n\t@: You (a mouse)\n\t!: Cat\n\t$: Cheese\n\t.: Unexplored space");
        System.out.println("Moves:\n\tUse W (up), A (left), S (down) and D (right) to move.\n\t(You must press enter after each move).");
        System.out.println();
    }
    public void cheeseCollected(int NumberCollected, int TotalNeeded){
        System.out.println("Cheese collected: " + NumberCollected + " of "+ TotalNeeded);
        System.out.println();
    }

    public static void invalidInput(){
        System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
    }
    public void getInputMsg(){
        System.out.print("Enter your move [WASD?]: ");
    }
    public void winMsg(){
        System.out.println("Congratulations! You won!");
    }
    public static void invalidMoveMsg(){
        System.out.println("Invalid move: you cannot move through walls!");
    }

    public void gotEatenMsg(){
        System.out.println("I'm sorry, you have been eaten!");
    }

    public void gameOverMsg(){
        System.out.println("GAME OVER; please try again.");
    }
}
