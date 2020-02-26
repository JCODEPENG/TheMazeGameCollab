package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeHandler {
    private int[][] baseMaze;
    private int unexploredRegion[][];
    private MazeGenerator maze;
    private Mouse player;
    private List<Cat> cats;
    private Cheese cheese;
    private int col = 20;
    private int row = 15;
    private final int PATH_SYMBOL = 2;
    private final int CAT_SYMBOL = 5;
    private final int PLAYER_SYMBOL = 4;
    private final int CHEESE_SYMBOL = 6;

    public static void main(String[] args) {
        MazeGenerator maze = new MazeGenerator(20, 15);
        int[][] Maze = maze.getMaze();
        for (int y = 0; y < 15; y++){
            for (int x = 0; x < 20; x++){
                System.out.print(Maze[x][y]);
            }
            System.out.println();
        }
    }
    public MazeHandler(){
       this.maze = new MazeGenerator(col, row);
       this.baseMaze = maze.getMaze();
       unvisitedSpots(col, row);
       this.player = new Mouse(1 ,1 , PLAYER_SYMBOL);
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
                return new int[]{0, -1};
            case(2):    //left
                return new int[]{-1, 0};
            case(3):    //down
                return new int[]{0, 1};
            case(4):    //right
                return new int[]{1, 0};
            default:
                return new int[]{0, 0,};
        }
    }

    public void updateCat(){
        ArrayList<Integer> choiceX = new ArrayList<Integer>();
        ArrayList<Integer> choiceY = new ArrayList<Integer>();
        int choice = 0;
        Random random = new Random();

        for (Cat cat : cats){
            choiceX.clear();
            choiceY.clear();
            cat.setLastX(cat.getX());
            cat.setLastY(cat.getY());
            for (int i = 1; i < 5; i++){
                int newX = cat.getX() + direction(i)[0];
                int newY = cat.getX() + direction(i)[1];

                if (!isWall(newX, newY) && !cat.didBackTrace()){
                    choiceX.add(newX);
                    choiceY.add(newY);
                }
            }
            choice = random.nextInt(choiceX.size());
            cat.move(choiceX.get(choice), choiceY.get(choice));
            if (cat.getX() == cat.getLastX() && cat.getY() == cat.getLastY()){
                cat.setBackTrace(true);
            } else{
                cat.setBackTrace(false);
            }
        }
    }

    public void updateCheese(){
        Random random = new Random();
        boolean CarryOn = true;
        while (CarryOn){
            int randomX = random.nextInt(col);
            int randomY = random.nextInt(row);
            if (baseMaze[randomX][randomY] == PLAYER_SYMBOL || isWall(randomX,randomY)){
                CarryOn = true;
            }
            else{
                cheese = new Cheese(randomX,randomY, CHEESE_SYMBOL);
                baseMaze[randomX][randomY] = cheese.GetSymbol();
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


    public void updateExploredRegions(){
        int currentX = this.player.getX();
        int currentY = this.player.getY();
        unexploredRegion[currentY][currentX] = 0;       //current
        unexploredRegion[currentY][currentX+1] = 0;     //
        unexploredRegion[currentY][currentX-1] = 0;
        unexploredRegion[currentY+1][currentX] = 0;
        unexploredRegion[currentY-1][currentX] = 0;
        unexploredRegion[currentY-1][currentX-1] = 0;
        unexploredRegion[currentY-1][currentX+1] = 0;
        unexploredRegion[currentY+1][currentX-1] = 0;
        unexploredRegion[currentY+1][currentX+1] = 0;
    }

    public boolean cheeseEaten(){
        if (player.getX() == cheese.GetX() && player.getY() == cheese.GetY()){
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


    public void unvisitedSpots(int x, int y){
        unexploredRegion = new int[x][y];
        for (int loopY = 1; loopY < y - 1; y++){
            for (int loopX = 1; loopX < x - 1; x++){
                unexploredRegion[loopX][loopY] = 1;
            }
        }
    }

    private void initCatList(){
        Cat catTopRight = new Cat(col - 2, 1, CAT_SYMBOL);
        Cat catBottomLeft = new Cat(1, row - 2, CAT_SYMBOL);
        Cat catBottomRight = new Cat(col - 2, row - 2, CAT_SYMBOL);
        cats.add(catTopRight);
        cats.add(catBottomLeft);
        cats.add(catBottomRight);
    }

    private void setSymbol(int x, int y, int symbol){
        baseMaze[x][y] = symbol;
    }
}
