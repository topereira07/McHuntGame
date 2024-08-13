package io.codeforall.forsome.Background;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    public static final int PADDING = 10;
    protected Picture background;

    public Background() {
        this.background = new Picture();
    }


    public Picture getBackground() {
        return background;
    }

    public void setBackground(Picture background) {
        this.background = background;
    }

    public void createBackground(){
        this.background.draw();
    }


}
