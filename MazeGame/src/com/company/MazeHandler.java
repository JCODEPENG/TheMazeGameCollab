package com.company;

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

    public void UpdatePlayer(int choice){
        int previousPositionX = player.GetX();
        int previousPositionY = player.GetY();

        if (choice == 1) {
            player.MoveUp();
            if (isWall(player.GetX(), player.GetY())) {
                player.MoveDown();
            } else {
                makePath(previousPositionX, previousPositionY);
                baseMaze[player.GetY()][player.GetX()] = player.GetSymbol();
            }
        }
        if(choice == 2) {
            player.MoveLeft();
            if (isWall(player.GetX(), player.GetY())) {
                player.MoveRight();
            } else {
                makePath(previousPositionX, previousPositionY);
                baseMaze[player.GetY()][player.GetX()] = player.GetSymbol();
            }
        }
        if(choice == 3) {
            player.MoveDown();
            if (isWall(player.GetX(), player.GetY())) {
                player.MoveUp();
            } else {
                makePath(previousPositionX, previousPositionY);
                baseMaze[player.GetY()][player.GetX()] = player.GetSymbol();
            }
        }
        if (choice == 4) {
            player.MoveRight();
            if (isWall(player.GetX(), player.GetY())) {
                player.MoveLeft();
            } else {
                makePath(previousPositionX, previousPositionY);
                baseMaze[player.GetY()][player.GetX()] = player.GetSymbol();
            }
        }

    }


    public void updateCat() {
        for (Cat cat : cats) {
            boolean catMoved = false;
            int previousPositionX = cat.GetX();
            int previousPositionY = cat.GetY();
            while (!catMoved) {
                cat.MoveRandom();
                if (isWall(cat.GetX(), cat.GetY())) {
                    cat.SetX(previousPositionX);
                    cat.SetY(previousPositionY);

                } else {
                    this.baseMaze[cat.GetY()][cat.GetX()] = cat.GetSymbol();
                    this.baseMaze[previousPositionY][previousPositionX] = 2;
                    catMoved = true;
                }
            }
        }
    }

    public void UpdateCheese(){
        Random random = new Random();
        boolean CarryOn = true;
        while (CarryOn){
            int randomX = random.nextInt(15);
            int randomY = random.nextInt(6);
            if (baseMaze[randomY][randomX] == player.GetSymbol() || isWall(randomX,randomY)){
                CarryOn = true;
            }
            else{
                cheese = new Cheese(randomX,randomY, CHEESE_SYMBOL);
                baseMaze[randomY][randomX] = cheese.GetSymbol();
                CarryOn = false;
            }
        }

    }

    public void updateExploredRegions(){
        int currentX = this.player.GetX();
        int currentY = this.player.GetY();
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
        if (player.GetX() == cheese.GetX() && player.GetY() == cheese.GetY()){
            return true;
        }
        return false;
    }

    public boolean playerEaten() {
        for (Cat cat : cats) {
            if (cat.GetX() == player.GetX() && cat.GetY() == player.GetY()) {
                this.baseMaze[cat.GetY()][cat.GetX()] = 7;
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

    private void makePath(int x, int y){
        baseMaze[x][y] = 2;
    }
}
