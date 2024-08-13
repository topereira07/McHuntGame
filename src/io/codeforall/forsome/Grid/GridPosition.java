package io.codeforall.forsome.Grid;

public interface GridPosition {
    public int getCol();
    public int getRow();
    public void setPos(int col, int row);
    public void show();
    public void hide();
    public void moveInDirection (GridDirection direction, int distance);
    public boolean equals(GridPosition position);
}
