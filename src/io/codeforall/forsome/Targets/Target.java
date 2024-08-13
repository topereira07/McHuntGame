package io.codeforall.forsome.Targets;

import io.codeforall.forsome.Grid.GameGrid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Target implements Destructible, Movable {
    // Propriedades
    private int x;
    private int y;
    private boolean isActive;
    private TargetType type;
    private GameGrid gameGrid;
    private String filepath;


    private int targetPoints;



    private Picture picture;
    private static final int STEP_SIZE = 20; // Tamanho do passo do movimento


    // Construtor
    public Target(int x, int y, TargetType type, String file, GameGrid gameGrid, int targetPoints) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isActive = true;
        this.gameGrid = gameGrid;
        this.filepath = file;
        this.targetPoints = targetPoints;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }

    public int getTargetPoints() {
        return targetPoints;
    }

    public void setTargetPoints(int targetPoints) {
        this.targetPoints = targetPoints;
    }

    @Override
    public void createTarget(int x,int y,String file) {
        this.picture = new Picture(x, y, file);
        this.picture.draw();
    }

    // NEW DELETE TARGET
    @Override
    public void deleteTarget() {
        this.isActive = false;
        this.picture.delete();
        System.out.println("Target at position (" + x + ", " + y + ") is deleted.");
    }

    @Override
    public void move() {
        if (isActive) {
            int xInitial = x;
            int yInitial = y;

            // Atualiza a posição no grid
            x += STEP_SIZE;
            if (x > gameGrid.getWidth() - 90) {
                this.targetPoints -= 10;
                x = -STEP_SIZE; // Reinicia a posição horizontal quando sai do lado direito da tela
                y = (int) Math.round(Math.random() * (gameGrid.getHeight()/2));
                if(type == TargetType.HENRIQUE){
                    deleteTarget();

                    return;
                }
            }

            int xDistancePixels = (x - xInitial);
            int yDistancePixels = (y - yInitial);

            this.picture.translate(xDistancePixels, yDistancePixels);


        } else {
            System.out.println("Cannot move. Target is inactive.");
        }
    }

    @Override
    public void checkCollision(Target target) {
        // Implementação do método de verificação de colisão



    }

    // Métodos adicionais que podem ser úteis
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive() {
        return isActive;
    }

    public void changeActive(boolean value){
        this.isActive = value;
    }

    public int getWidth(){
        return this.picture.getWidth();
    }

    public int getHeight(){
        return this.picture.getHeight();
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}


