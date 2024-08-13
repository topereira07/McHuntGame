package io.codeforall.forsome.Grid;

public abstract class AbstractGridPosition implements GridPosition{
    private int col;
    private int row;
    private Grid grid;

    public AbstractGridPosition(int col, int row, Grid grid){
        this.col = col;
        this.row = row;
        this.grid = grid;
    }

    public Grid getGrid(){
        return grid;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void setPos(int col, int row) {
        this.col = col;
        this.row = row;
        show();
    }

    public abstract void show();

    @Override
    public abstract void hide();

    @Override
    public void moveInDirection(GridDirection direction, int distance) {
        /*switch (direction){
            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case LEFT:
                moveLeft(ditance);
                break;
            case RIGHT:
                moveRight(distance);
        }*/
    }

    @Override
    public boolean equals(GridPosition position) {
        return this.col == position.getCol() && this.row == position.getRow() ? true : false;
    }
}
