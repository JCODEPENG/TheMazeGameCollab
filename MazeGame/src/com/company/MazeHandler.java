package com.company;

import java.util.ArrayList;
import java.util.Random;

public class MazeHandler {
    private int[][] baseMaze;
    private int unexploredRegion[][];
    private MazeGenerator maze;
    private Mouse player;
    private ArrayList<Cat> cats;
    private Cheese cheese;
    private int col = 15;
    private int row = 20;
    private final int PATH_SYMBOL = 1;
    private final int EXPLORED_REGION = 2;
    private final int UNEXPLORED_REGION = 3;
    private final int CAT_SYMBOL = 5;
    private final int PLAYER_SYMBOL = 4;
    private final int CHEESE_SYMBOL = 6;
    private final int DEATH_SYMBOL = 7;

//    public static void main(String[] args) {
//        MazeGenerator maze = new MazeGenerator(15, 20);
//        int[][] Maze = maze.getMaze();
//        for (int y = 0; y < 15; y++){
//            for (int x = 0; x < 20; x++){
//                System.out.print(Maze[y][x]);
//            }
//            System.out.println();
//        }
//    }

    public MazeHandler(){
       this.maze = new MazeGenerator(col, row);


       this.baseMaze = maze.getMaze();

       unvisitedSpots(col, row);
       printUMaze();
       this.player = new Mouse(1 ,1 , PLAYER_SYMBOL);
       this.baseMaze[player.getY()][player.getX()] = player.getSymbol();
       this.cats = new ArrayList<Cat>();
       initCatList();
    }

    public boolean updatePlayer(int choice){

        int nextX = direction(choice)[0] + player.getX();
        int nextY = direction(choice)[1] + player.getY();

        if (!isWall(nextX, nextY)){
            setSymbol(player.getX(), player.getY(), PATH_SYMBOL);
            player.move(nextX, nextY);
            setSymbol(player.getX(), player.getY(), PLAYER_SYMBOL);
            return true;
        }
        return false;


    }

    private int[] direction(int choice){
        switch(choice){
            case(1):    //up
                return new int[]{-1, 0};
            case(2):    //left
                return new int[]{0, -1};
            case(3):    //down
                return new int[]{1, 0};
            case(4):    //right
                return new int[]{0, 1};
            default:
                return new int[]{0, 0,};
        }
    }
/*
    public void updateCat(){

        ArrayList<Integer> choiceX = new ArrayList<Integer>();
        ArrayList<Integer> choiceY = new ArrayList<Integer>();
        int choice = 0;
        Random random = new Random();

        for (Cat cat : cats){
            System.out.println(cat.getX() + "cat was that and player is" + player.getX() + " ");
            System.out.println(cat.getY() + "cat was that and player is" + player.getY() + " ");
            choiceX.clear();
            choiceY.clear();
            cat.setLastX(cat.getX());
            cat.setLastY(cat.getY());
            for (int i = 1; i < 5; i++){
                int newX = cat.getX() + direction(i)[0];
                int newY = cat.getY() + direction(i)[1];
                //swapped here
                if (!isWall(newY, newX) && !cat.didBackTrace()){
                    choiceX.add(newX);
                    choiceY.add(newY);
                }
            }
            choice = random.nextInt(choiceX.size());
            setSymbol(cat.getX(), cat.getY(), PATH_SYMBOL);
            cat.move(choiceX.get(choice), choiceY.get(choice));
            setSymbol(cat.getX(), cat.getY(), CAT_SYMBOL);
            if (cat.getX() == cat.getLastX() && cat.getY() == cat.getLastY()){
                cat.setBackTrace(true);
            } else{
                cat.setBackTrace(false);
            }

        }
       // printMaze();
    }
*/

    public void updateCheese(){

        Random random = new Random();
        boolean CarryOn = true;
        while (CarryOn){
            int randomX = random.nextInt(row);
            int randomY = random.nextInt(col);
            if (baseMaze[randomY][randomX] == PLAYER_SYMBOL || isWall(randomY,randomX)){
                CarryOn = true;
            }
            else{
                cheese = new Cheese(randomX,randomY, CHEESE_SYMBOL);
                baseMaze[randomY][randomX] = cheese.getSymbol();
                CarryOn = false;
            }
        }


    }

    public int[][] returnBaseMaze(){
        return this.baseMaze;
    }

    public int[][] returnUnexploredRegion(){
        return this.unexploredRegion;
    }
    public int returnUnexploredSymbol(){
        return UNEXPLORED_REGION;
    }

    public void updateExploredRegions(){
        int currentX = this.player.getX();
        int currentY = this.player.getY();
        unexploredRegion[currentX][currentY] = EXPLORED_REGION;       //current
        unexploredRegion[currentX - 1][currentY] = EXPLORED_REGION;       //left
        unexploredRegion[currentX + 1][currentY] = EXPLORED_REGION;       //right
        unexploredRegion[currentX][currentY - 1] = EXPLORED_REGION;       //top
        unexploredRegion[currentX][currentY + 1] = EXPLORED_REGION;       //down
        unexploredRegion[currentX - 1][currentY - 1] = EXPLORED_REGION;       //top-left
        unexploredRegion[currentX + 1][currentY - 1] = EXPLORED_REGION;       //top-right
        unexploredRegion[currentX - 1][currentY + 1] = EXPLORED_REGION;       //bottom-left
        unexploredRegion[currentX + 1][currentY + 1] = EXPLORED_REGION;       //bottom-right
        //printUMaze();

    }

    public boolean cheeseEaten(){
        if (player.getY() == cheese.getX() && player.getX() == cheese.getY()){
            return true;
        }
        return false;
    }

    public boolean playerEaten() {
        for (Cat cat : cats) {
            if (cat.getX() == player.getX() && cat.getY() == player.getY()) {
                this.baseMaze[cat.getX()][cat.getY()] = 7;
                return true;
            }

        }
        return false;
    }

    public boolean isWall(int PosX, int PosY){
        return maze.isWall(PosX, PosY);
    }


    public void unvisitedSpots(int y, int x){
        this.unexploredRegion = new int[col][row];
        System.out.println(y);
        System.out.println(x);
        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                if (i == 0 || i == y - 1){
                    unexploredRegion[i][j] = EXPLORED_REGION;
                }
                else if (j == 0 || j == x - 1){
                    unexploredRegion[i][j] = EXPLORED_REGION;
                }
                else {
                    unexploredRegion[i][j] = UNEXPLORED_REGION;
                }
            }
        }
        //printUMaze();

    }
    public void printUMaze(){
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 20; j++){
                System.out.print(unexploredRegion[i][j]);
            }
            System.out.println();
        }
    }
    private void initCatList(){
        Cat catTopRight = new Cat(1, col-2, CAT_SYMBOL);
        Cat catBottomLeft = new Cat(row-2, 1, CAT_SYMBOL);
        Cat catBottomRight = new Cat(row -2, col - 2, CAT_SYMBOL);
        cats.add(catTopRight);
        cats.add(catBottomLeft);
        cats.add(catBottomRight);
        baseMaze[catTopRight.getY()][catTopRight.getX()] = CAT_SYMBOL;
        baseMaze[catBottomLeft.getY()][catBottomLeft.getX()] = CAT_SYMBOL;
        baseMaze[catBottomRight.getY()][catBottomRight.getX()] = CAT_SYMBOL;


    }

    private void setSymbol(int x, int y, int symbol){
        baseMaze[x][y] = symbol;
    }
}
