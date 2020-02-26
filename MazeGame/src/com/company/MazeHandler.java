package com.company;

import java.util.ArrayList;
import java.util.Random;

public class MazeHandler {
    private int[][] baseMaze;
    private int[][] unexploredRegion;
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

    public MazeHandler(){
       this.maze = new MazeGenerator(col, row);
       this.baseMaze = maze.getMaze();
       unvisitedSpots(col, row);
       this.player = new Mouse(1 ,1 , PLAYER_SYMBOL);
       this.baseMaze[player.getY()][player.getX()] = player.getSymbol();
       updateCheese();
       this.cats = new ArrayList<Cat>();
       initCatList();
    }

    public boolean updatePlayer(int choice){

        int nextX = direction(choice)[0] + player.getX();
        int nextY = direction(choice)[1] + player.getY();

        if (!isWall(nextY, nextX)){
            setSymbol(player.getY(), player.getX(), PATH_SYMBOL);
            player.move(nextX, nextY);
            setSymbol(player.getY(), player.getX(), PLAYER_SYMBOL);
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
                int newY = cat.getY() + direction(i)[1];
                //swapped here
                if (!isWall(newY, newX) && !cat.didBackTrace()){
                    choiceX.add(newX);
                    choiceY.add(newY);
                }
            }
            choice = random.nextInt(choiceX.size());
            if(catOnCheese(cat.getX(), cat.getY())){
                setSymbol(cat.getY(), cat.getX(), CHEESE_SYMBOL);
                cat.move(choiceX.get(choice), choiceY.get(choice));
                setSymbol(cat.getY(), cat.getX(), CAT_SYMBOL);
            }
            else{
                setSymbol(cat.getY(), cat.getX(), PATH_SYMBOL);
                cat.move(choiceX.get(choice), choiceY.get(choice));
                setSymbol(cat.getY(), cat.getX(), CAT_SYMBOL);
            }

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

    public int returnPlayerSymbol(){
        return PLAYER_SYMBOL;
    }
    public int returnCheeseSymbol(){
        return CHEESE_SYMBOL;
    }
    public int returnCatSymbol(){
        return CAT_SYMBOL;
    }
    public int returnExploredSymbol(){
        return EXPLORED_REGION;
    }

    public void updateExploredRegions(){
        int currentX = this.player.getX();
        int currentY = this.player.getY();
        unexploredRegion[currentY][currentX] = EXPLORED_REGION;       //current
        unexploredRegion[currentY - 1][currentX] = EXPLORED_REGION;       //left
        unexploredRegion[currentY + 1][currentX] = EXPLORED_REGION;       //right
        unexploredRegion[currentY][currentX - 1] = EXPLORED_REGION;       //top
        unexploredRegion[currentY][currentX + 1] = EXPLORED_REGION;       //down
        unexploredRegion[currentY - 1][currentX - 1] = EXPLORED_REGION;       //top-left
        unexploredRegion[currentY + 1][currentX - 1] = EXPLORED_REGION;       //top-right
        unexploredRegion[currentY - 1][currentX + 1] = EXPLORED_REGION;       //bottom-left
        unexploredRegion[currentY + 1][currentX + 1] = EXPLORED_REGION;       //bottom-right
    }

    public boolean cheeseEaten(){
        if (player.getY() == cheese.getY() && player.getX() == cheese.getX()){
            return true;
        }
        return false;
    }

    public boolean playerEaten() {
        for (Cat cat : cats) {
            if (cat.getX() == player.getX() && cat.getY() == player.getY()) {
                this.baseMaze[cat.getY()][cat.getX()] = DEATH_SYMBOL;
                return true;
            }

        }
        return false;
    }

    public boolean catOnCheese(int getX, int getY){
        if (getX == cheese.getX() && getY == cheese.getY()) {
            return true;
        }

        return false;
    }

    public boolean isWall(int PosX, int PosY){
        return maze.isWall(PosX, PosY);
    }

    public void unvisitedSpots(int y, int x){
        this.unexploredRegion = new int[col][row];
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
