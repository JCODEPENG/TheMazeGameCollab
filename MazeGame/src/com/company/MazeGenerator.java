package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *Maze generator class generates the maze by using the depth first search
 *the maze array is created once the class is constructed so no need to call generate
 * used a stack instead of recursion to prevent stack overflow from recursive calls
 */
public class MazeGenerator{
    private int col;
    private int row;
    private int[][] maze;
    private Stack<Integer> inCompleteX;
    private Stack<Integer> inCompleteY;
    private ArrayList<Integer> neighborX;
    private ArrayList<Integer> neighborY;
    private final int WALL = 0;
    private final int PATH = 1;

    public MazeGenerator(int col, int row){
        this.col = col;
        this.row = row;
        this.maze = new int[col][row];
        this.inCompleteX = new Stack<Integer>();
        this.inCompleteY = new Stack<Integer>();
        this.neighborX = new ArrayList<Integer>();
        this.neighborY = new ArrayList<Integer>();
        generate();
    }

    public void generate(){
        maze[1][1] = PATH;                //player init

        pushStacks(1, 1);
        int currentX = 1;
        int currentY = 1;
        int next;
        while (!inCompleteX.empty()) {
            findNeighbor(currentX, currentY);
            while (hasNeighbor()) {
                next = randomNeighbor();
                makePath(currentX, currentY, next);
                if (!isComplete()){
                    pushStacks(currentX, currentY);
                }

                currentX = neighborX.get(next);
                currentY = neighborY.get(next);
                findNeighbor(currentX, currentY);
            }
            currentX = inCompleteX.pop();
            currentY = inCompleteY.pop();
        }
        maze[col - 2][1] = PATH;          //top-right cat
        maze[1][row - 2] = PATH;          //bottom-left cat
        maze[col - 2][row - 2] = PATH;    //bottom-right cat

        for (int i = 0; i < (row*col/10); i ++){
            randomRemoveWall();
        }
    }

    public boolean isWall(int x, int y){
        return (maze[x][y] == WALL);
    }

    public int[][] getMaze(){
        return maze;
    }


    private void pushStacks(int x, int y){
        inCompleteX.push(x);
        inCompleteY.push(y);
    }

    private void makePath(int x, int y, int next){
        int nextX = neighborX.get(next);
        int nextY = neighborY.get(next);
        int wallX = x - (x - nextX) / 2;
        int wallY = y - (y - nextY) / 2;
        maze[wallX][wallY] = PATH;
        maze[nextX][nextY] = PATH;
    }

    private void findNeighbor(int x, int y){
        neighborX.clear();
        neighborY.clear();

        if (isInBound(x - 2, y) && isWall(x - 2, y)){   // left
            neighborX.add(x - 2);
            neighborY.add(y);
        }
        if (isInBound(x + 2, y) && isWall(x + 2, y)){   //right
            neighborX.add(x + 2);
            neighborY.add(y);
        }
        if (isInBound(x, y + 2) && isWall(x, y + 2)) {   //top
            neighborX.add(x);
            neighborY.add(y + 2);
        }
        if (isInBound(x, y - 2) && isWall(x, y - 2)){   //down
            neighborX.add(x);
            neighborY.add(y - 2);
        }
    }

    private boolean isComplete(){
        return (neighborX.size() == 1);
    }

    private boolean hasNeighbor(){
        return !neighborX.isEmpty();
    }

    private int randomNeighbor(){
        Random random = new Random();
        return random.nextInt(neighborX.size());
    }

    private  boolean isInBound(int x, int y){
        if (x < 1 || x > col - 2 || y < 1 || y > row - 2) {
            return false;
        }
        return true;
    }

    private void randomRemoveWall(){
        Random random = new Random();
        boolean isRemoved = false;

        while (!isRemoved){
            int ranX;
            int ranY;
            while(true) {
                ranX = random.nextInt(col);
                ranY = random.nextInt(row);
                if (maze[ranX][ranY] == WALL){
                    break;
                }
            }
            boolean left = false;
            boolean right = false;
            boolean top = false;
            boolean down = false;

            if (isInBound(ranX - 1, ranY) && isWall(ranX - 1, ranY)){   // left
                left = true;
            }
            if (isInBound(ranX + 1, ranY) && isWall(ranX + 1, ranY)){   //right
                right = true;
            }
            if (isInBound(ranX, ranY + 1) && isWall(ranX, ranY + 1)) {   //top
                top = true;
            }
            if (isInBound(ranX, ranY - 1) && isWall(ranX, ranY - 1)){   //down
                down = true;
            }
            if ((left || top) && (left || down) && (right || top) && (right || down)){
                isRemoved = true;
                maze[ranX][ranY] = PATH;
            }
        }
    }


}
