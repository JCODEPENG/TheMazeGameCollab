package com.company;

public class DisplayOutput {
    public void OutputMaze(int[][] Maze){
        assert(Maze != null);
        int[] YDimension = Maze[0];
        int YSize = YDimension.length;
        int XSize = Maze.length;
        for  (int i = 0; i < YSize; i++){
            for (int j = 0; j < XSize; j++){
                if (Maze[i][j] == 1){
                    System.out.println('.');
                }
                if (Maze[i][j] == 2){
                    System.out.println(' ');
                }
                if (Maze[i][j] == 3){
                    System.out.println('#');
                }
                if(Maze[i][j] == 4){
                    System.out.println('@');
                }
                if (Maze[i][j] == 5){
                    System.out.println('!');
                }
                if (Maze[i][j] == 6){
                    System.out.println('C');
                }
                if (Maze[i][j] == 7){
                    System.out.println('X');
                }
            }
            System.out.println();
        }


    }

    public void WelcomeMsg(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Cat and Mouse Maze Adventure!\nBy Joshua Peng and Jocelyn Gau");
        System.out.println("----------------------------------------");
    }

    public void HelpMsg(){
        System.out.println("Directions:\n\tFind 5 cheese before a cat eats you!");
        System.out.println("Legend:\n\t#: Wall\n\t@: You (a mouse)\n\t!: Cat\n\t$: Cheese\n\t.: Unexplored space");
        System.out.println("Moves:\n\tUse W (up), A (left), S (down) and D (right) to move.\n\t(You must press enter after each move).");
    }
    public void CheeseCollected(int NumberCollected, int TotalNeeded){
        System.out.println("Cheese collected: " + NumberCollected + " of "+ TotalNeeded);
    }

    public void GetInputMsg(){
        System.out.print("Enter your move [WASD?]: ");
    }
    public void WinMsg(){
        System.out.println("Congratulations! You won!");
    }
    public void InvalidMoveMsg(){
        System.out.println("Invalid move: you cannot move through walls!");
    }

    public void GotEatenMsg(){
        System.out.println("I'm sorry, you have been eaten!");
    }

    public void GameOverMsg(){
        System.out.println("GAME OVER; please try again.");
    }
}
