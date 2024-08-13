package io.codeforall.forsome.Targets;

import io.codeforall.forsome.Grid.GameGrid;

import java.util.Random;

public class TargetFactory {

    public static Target createTarget(GameGrid gameGrid) {
        Random random = new Random();
        TargetType type = TargetType.values()[random.nextInt(TargetType.values().length)];
        String file = type.getPath();
        int targetScore = type.getPoints();
        // Define a posição inicial do alvo fora da tela
        int x = -20; // Começa fora da tela à esquerda
        int y = (int) Math.round(Math.random() * (gameGrid.getHeight()/2)); // Em uma posição aleatória verticalmente

        return new Target(x, y, type, file, gameGrid,targetScore);
    }
}
