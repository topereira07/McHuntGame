package io.codeforall.forsome.Targets;

import io.codeforall.forsome.Grid.GameGrid;

public class PapaNozk extends Target{


    public PapaNozk(int x, int y, String file,GameGrid gameGrid, int targetPoints) {
        super(x, y,TargetType.PAPANOZK, file, gameGrid, targetPoints);
    }
}
