package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator{
    private int row ;
    private int col ;
    private int[][] maze;
    private Stack<Integer> inCompleteX;
    private Stack<Integer> inCompleteY;
    private ArrayList<Integer> listX;
    private ArrayList<Integer> listY;
    private int wall = 0;
    private int path = 1;

    public MazeGenerator(int row, int col){
        this.row = row;
        this.col = col;
        this.maze = new int[this.col][this.row];
        this.inCompleteX = new Stack<Integer>();
        this.inCompleteY = new Stack<Integer>();
        this.listX = new ArrayList<Integer>();
        this.listY = new ArrayList<Integer>();
        generate();
        }

    public void generate(){
        maze[1][1] = path;                //player init

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

                currentX = listX.get(next);
                currentY = listY.get(next);
                findNeighbor(currentX, currentY);
            }
            currentX = inCompleteX.pop();
            currentY = inCompleteY.pop();
        }
        maze[col - 2][1] = path;          //top-right cat
        maze[1][row - 2] = path;          //bottom-left cat
        maze[col - 2][row - 2] = path;    //bottom-right cat

        for (int i = 0; i < row; i ++){
            randomRemoveWall();
        }
    }

    private void pushStacks(int x, int y){
        inCompleteX.push(x);
        inCompleteY.push(y);
    }

    private void makePath(int x, int y, int next){
        int nextX = listX.get(next);
        int nextY = listY.get(next);
        int wallX = x - (x - nextX) / 2;
        int wallY = y - (y - nextY) / 2;
        maze[wallX][wallY] = path;
        maze[nextX][nextY] = path;
    }

    private void findNeighbor(int x, int y){
        listX.clear();
        listY.clear();

        if (isInBound(x - 2, y) && isWall(x - 2, y)){   // left
            listX.add(x - 2);
            listY.add(y);
        }
        if (isInBound(x + 2, y) && isWall(x + 2, y)){   //right
            listX.add(x + 2);
            listY.add(y);
        }
        if (isInBound(x, y + 2) && isWall(x, y + 2)) {   //top
            listX.add(x);
            listY.add(y + 2);
        }
        if (isInBound(x, y - 2) && isWall(x, y - 2)){   //down
            listX.add(x);
            listY.add(y - 2);
        }
    }

    private boolean isComplete(){
        return (listX.size() == 1);
    }

    private boolean hasNeighbor(){
        return !listX.isEmpty();
    }

    private int randomNeighbor(){
        Random random = new Random();
        return random.nextInt(listX.size());
    }

    private  boolean isInBound(int x, int y){
        if (x < 1 || x > col - 2 || y < 1 || y > row - 2) {
            return false;
        }
        return true;
    }

    private boolean isWall(int x, int y){
        return (maze[x][y] == wall);
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
                if (maze[ranX][ranY] == wall){
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
                maze[ranX][ranY] = path;
//                System.out.println("x: " + ranX + " Y: " + ranY);
            }
        }
    }

    public int[][] getMaze(){
        return maze;
    }
}
