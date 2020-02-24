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
    private int wall;
    private int path;

    public MazeGenerator(int row, int col){
        this.row = row;
        this.col = col;
        this.maze = new int[this.col][this.row];
        this.inCompleteX = new Stack<Integer>();
        this.inCompleteY = new Stack<Integer>();
        this.listX = new ArrayList<Integer>();
        this.listY = new ArrayList<Integer>();
        this.wall = 0;
        this.path = 1;
        }

    public void generate(){
        this.maze[1][1] = this.path;                //player init

        pushStacks(1, 1);
        int currentX = 1;
        int currentY = 1;
        int next;
        while (!this.inCompleteX.empty()) {
            findNeighbor(currentX, currentY);
            while (hasNeighbor()) {
                next = randomNeighbor();
                makePath(currentX, currentY, next);
                if (!isComplete()){
                    pushStacks(currentX, currentY);
                }

                currentX = this.listX.get(next);
                currentY = this.listY.get(next);
                findNeighbor(currentX, currentY);
            }
            currentX = this.inCompleteX.pop();
            currentY = this.inCompleteY.pop();
        }
        this.maze[this.col - 2][1] = this.path;          //top-right cat
        this.maze[1][this.row - 2] = this.path;          //bottom-left cat
        this.maze[this.col - 2][this.row - 2] = this.path;    //bottom-right cat

        for (int i = 0; i < this.row; i ++){
            randomRemoveWall();
        }
    }

    private void pushStacks(int x, int y){
        this.inCompleteX.push(x);
        this.inCompleteY.push(y);
    }

    private void makePath(int x, int y, int next){
        int nextX = this.listX.get(next);
        int nextY = this.listY.get(next);
        int wallX = x - (x - nextX) / 2;
        int wallY = y - (y - nextY) / 2;
        this.maze[wallX][wallY] = this.path;
        this.maze[nextX][nextY] = this.path;
    }

    private void findNeighbor(int x, int y){
        this.listX.clear();
        this.listY.clear();

        if (isInBound(x - 2, y) && isWall(x - 2, y)){   // left
            this.listX.add(x - 2);
            this.listY.add(y);
        }
        if (isInBound(x + 2, y) && isWall(x + 2, y)){   //right
            this.listX.add(x + 2);
            this.listY.add(y);
        }
        if (isInBound(x, y + 2) && isWall(x, y + 2)) {   //top
            this.listX.add(x);
            this.listY.add(y + 2);
        }
        if (isInBound(x, y - 2) && isWall(x, y - 2)){   //down
            this.listX.add(x);
            this.listY.add(y - 2);
        }
    }

    private boolean isComplete(){
        return (this.listX.size() == 1);
    }

    private boolean hasNeighbor(){
        return !this.listX.isEmpty();
    }

    private int randomNeighbor(){
        Random random = new Random();
        return random.nextInt(this.listX.size());
    }

    private  boolean isInBound(int x, int y){
        if (x < 1 || x > this.col - 2 || y < 1 || y > this.row - 2) {
            return false;
        }
        return true;
    }

    private boolean isWall(int x, int y){
        return (this.maze[x][y] == this.wall);
    }

    private void randomRemoveWall(){
        Random random = new Random();
        boolean isRemoved = false;

        while (!isRemoved){
            int ranX;
            int ranY;
            while(true) {
                ranX = random.nextInt(this.col);
                ranY = random.nextInt(this.row);
                if (this.maze[ranX][ranY] == this.wall){
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
                this.maze[ranX][ranY] = this.path;
//                System.out.println("x: " + ranX + " Y: " + ranY);
            }
        }
    }

    public void outputMaze(){
        for (int y = 0; y < this.row; y++){
            for (int x = 0; x < this.col; x++){
                System.out.print(this.maze[x][y]);
            }
            System.out.println("");
        }
    }
}
