package io.codeforall.forsome.Targets;

import io.codeforall.forsome.Grid.GameGrid;

public class Mekie extends Target {
    public Mekie(int x, int y, String file, GameGrid gameGrid, int targetPoints) {
        super(x, y,TargetType.MEKIE, file, gameGrid,targetPoints);
    }

}
