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
            }
            System.out.println();
        }


    }

    public void WelcomeMsg(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Cat and Mouse Maze Adventure!\n By Joshua Peng and Jocelyn Gau");
        System.out.println("----------------------------------------");
    }
}
