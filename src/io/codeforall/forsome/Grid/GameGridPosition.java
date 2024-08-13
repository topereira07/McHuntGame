package io.codeforall.forsome.Grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GameGridPosition extends AbstractGridPosition {
    private Rectangle rectangle;
    private GameGrid gameGrid;

    public GameGridPosition(int col,int row,GameGrid grid){
        super(col,row,grid);
        this.gameGrid = grid;
        this.rectangle = new Rectangle(gameGrid.columnToX(getCol()), gameGrid.rowToY(getRow()),gameGrid.getCellSize(),gameGrid.getCellSize());
        show();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
