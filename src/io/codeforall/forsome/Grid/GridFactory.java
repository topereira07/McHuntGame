package io.codeforall.forsome.Grid;

public class GridFactory {
    public static Grid makeGrid(int cols, int rows){
        return new GameGrid(cols,rows);
    }
}
