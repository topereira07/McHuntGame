package io.codeforall.forsome.Background;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class StartMenu extends Background{

    private Picture logo;
    private Picture announcement;
    private Text currentHighScore;
    private Picture instructions;

    public StartMenu(){
        this.background = new Picture(PADDING,PADDING,"resources/duck-hunt.png");
    }

    @Override
    public void createBackground(){
        super.createBackground();
        createLogo();
        createAnnouncement();
        createInstructions();
        powered();
    }

    public void createLogo() {
        this.logo = new Picture(PADDING,PADDING,"resources/logo.png");
        this.logo.draw();
        this.logo.translate(170,100);

    }

    public void createAnnouncement(){
        this.announcement = new Picture(222,340, "resources/start-announcement.png");
        this.announcement.draw();

    }

    public void createInstructions(){
        this.instructions = new Picture(630,0,"resources/instructions-small.png");
        this.instructions.draw();
        this.instructions.grow(-10,-10);
    }

    public void setCurrentHighScore(int currentHighScore){
        String str = "Current Highscore: " + currentHighScore;
        this.currentHighScore = new Text(50,20,str);
        this.currentHighScore.grow(30,20);
        this.currentHighScore.draw();
    }

    public void powered(){
        Picture bananas = new Picture(315,430,"resources/powered-bananas-small.png");
        bananas.draw();
    }
}
