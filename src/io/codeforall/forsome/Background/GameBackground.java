package io.codeforall.forsome.Background;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameBackground extends Background {

    public GameBackground(){
        this.background = new Picture(PADDING,PADDING,"resources/duck-hunt-full.png");
    }
    @Override
    public void createBackground() {
        super.createBackground();
        createGun();

    }

    public void createGun() {
        Picture weapon = new Picture();
        weapon.load("resources/gun.png");
        weapon.draw();
        weapon.grow(-150,-150);
        weapon.translate(190,248);
    }
}
