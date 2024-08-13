package io.codeforall.forsome.Grid;

public interface Grid {

    public void init();
    public int getCols();
    public int getRows();
    public GridPosition makeGridPosition(int col, int row);

}
