package io.codeforall.forsome.Background;

import io.codeforall.forsome.Player;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameOverBackground extends Background {
    private int finalScore;

    public GameOverBackground() {
        this.background = new Picture(PADDING, PADDING, "resources/gameOver1.png");
        //this.backgroud.grow();
    }

    @Override
    public void createBackground() {
        super.createBackground();

    }
    public void setFinalScore(int score){
        this.finalScore=score;
    }
    public int getFinalScore(){
        return this.finalScore;
    }


     // in the Game class, when gameOver update the player score = this.finalScore
    //  update also game over boolean to true..



    //

}