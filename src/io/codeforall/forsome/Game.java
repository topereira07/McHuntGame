package io.codeforall.forsome;

import io.codeforall.forsome.Background.Background;
import io.codeforall.forsome.Background.GameBackground;
import io.codeforall.forsome.Background.GameOverBackground;
import io.codeforall.forsome.Grid.Grid;
import io.codeforall.forsome.Grid.GameGrid;
import io.codeforall.forsome.Grid.GridFactory;
import io.codeforall.forsome.Targets.Target;
import io.codeforall.forsome.Targets.TargetFactory;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Text;
import io.codeforall.forsome.Background.StartMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public final int MAX_TARGETS = 20;
    private Grid grid;
    private int delay;
    private Player player;
    private List<Target> targets;
    private Background background;
    private int highScore;
    private int currentScore;
    private boolean gameOver;
    private int maxTargets;
    private GameState gameState;
    private Text playerScore;
    private Text bullets;

    public Game(int cols, int rows, int delay) {
        createCanvas(cols, rows);
        this.grid = GridFactory.makeGrid(cols, rows);
        this.delay = delay;
        this.gameOver = false;
        this.gameState = GameState.START;
        this.maxTargets = MAX_TARGETS;

    }

    public void createCanvas(int cols, int rows) {
        Canvas.getInstance();

        if (grid instanceof GameGrid) {
            GameGrid gameGrid = (GameGrid) grid;
            Canvas.limitCanvasWidth(gameGrid.rowToY(rows));
            Canvas.limitCanvasHeight(gameGrid.columnToX(cols));
        }
    }

    public void startGame() throws InterruptedException {
        this.player = new Player(0, grid, false);
        this.highScore = Integer.parseInt(loadHighScore());
        System.out.println(this.highScore);

        GameGrid gameGrid = null;
        if (grid instanceof GameGrid) {
            gameGrid = (GameGrid) grid;
        }

        if (this.gameState == GameState.START) {
            this.background = new StartMenu();
            this.background.createBackground();


            if (this.background instanceof StartMenu) {
                StartMenu startMenu = (StartMenu) this.background;
                startMenu.setCurrentHighScore(this.highScore);
            }


            while (this.gameState == GameState.START) {
                Thread.sleep(this.delay);
                changeState();
            }
        }

        if (this.gameState == GameState.GAME) {
            grid.init();
            this.background = new GameBackground();
            this.background.createBackground();
            this.currentScore = 0;
            this.maxTargets = MAX_TARGETS;
            this.player.setPlaying(true);
            this.targets = new ArrayList<>(MAX_TARGETS);

            for (int i = 0; i < MAX_TARGETS; i++) {
                targets.add(TargetFactory.createTarget(gameGrid));
            }

            this.player = new Player(0, grid, true);
            this.player.getWeapon().drawBullets();
            playerScore();

            while (isGameRunning() && !this.gameOver) {

                for (Target target : targets) {
                    target.createTarget(target.getX(), target.getY(), target.getFilepath());
                    while (target.isActive() && player.isPlaying()) {
                        player.getWeapon().getAimer().move();
                        player.shoot(target);
                        target.move();
                        this.player.getWeapon().reloadWarning();

                        Thread.sleep(this.delay);

                        if (!player.isPlaying()) {
                            this.gameOver = true;
                        }

                        playerScore();
                        playerBullets();

                        if (this.player.getWeapon().getBulletsLeft() == 0) {
                            this.gameOver = true;
                            break;
                        }
                    }
                    if (!target.isActive()) {
                        target.deleteTarget();
                        player.removeTarget(target);
                    }

                }
                this.gameState = GameState.GAMEOVER;
                for (Target target : targets) {
                    target.deleteTarget();
                    player.removeTarget(target);
                }
            }

        }

        if (this.gameState == GameState.GAMEOVER) {
            this.background = new GameOverBackground();
            this.background.createBackground();


            String presentHighScore = checkHighScore();

            Text highScoreText = new Text(390, 100, presentHighScore);
            highScoreText.draw();
            highScoreText.grow(200, 40);
            playerScore();

            while (this.gameState == GameState.GAMEOVER) {
                Thread.sleep(this.delay);
                restart();
            }
            this.background = new StartMenu();
            this.background.createBackground();
        }
        // Garantir que a JVM termine quando o jogo acabar
        System.exit(0); // Adicionado: Chamar System.exit(0) para finalizar a JVM

    }

    private void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    private enum GameState {
        START,
        GAME,
        GAMEOVER
    }

    public void changeState() {

        if (this.player.changeGameState()) {
            this.gameState = GameState.GAME;
        }

    }

    public String checkHighScore() {

        if (this.currentScore > highScore) {
            highScore = this.currentScore;
                saveHighScore();
            return "NEW HIGHSCORE: " + this.highScore;
        }
        return "HIGHSCORE: " + this.highScore;
    }

    public void playerScore() {
        if (this.playerScore != null) {
            this.playerScore.delete();

        }
        this.currentScore = this.player.getScore();
        String score = "Score: " + this.currentScore;
        this.playerScore = new Text(680, 30, score);
        this.playerScore.draw();
        playerScore.grow(50, 20);
    }

    public void playerBullets() {
        if (this.bullets != null) {
            this.bullets.delete();
        }

        String bulletsLeft = "" + this.player.getWeapon().getBulletsLeft();
        this.bullets = new Text(50, 580, bulletsLeft);
        this.bullets.draw();
        this.bullets.grow(30, 20);
    }

    public void restart() throws InterruptedException {
        if (player.getRestart()) {
            this.gameState = GameState.START;
            this.gameOver = false;
            startGame();
        }
    }

    public void saveHighScore() {

        String filePath = getSaveFilePath();

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            String highScoreString = "" + this.highScore;
            byte[] buffer = highScoreString.getBytes();
            outputStream.write(buffer);

            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadHighScore() {

        String filePath = getSaveFilePath();

        if(createSaveFileIfNotExists(filePath)){
            System.out.println("Highscore file created");
        }

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            if(line == null){
                return "0";
            }

            reader.close();
            return line;

        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public boolean isGameRunning() {
        return this.gameState != GameState.GAMEOVER;
    }
    ///////////////////
    //getting the jar directory
    public String getJarDirectory() {
        try {
            File file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            return file.getParent();
        } catch (Exception e) {
            // Handle exceptions (e.g., print an error message)
            System.out.println("Error getting JAR directory: " + e.getMessage());
            return null;
        }
    }

    public String getSaveFilePath() {
        String jarLocation = getJarDirectory();
        if (jarLocation != null) {
            return jarLocation + File.separator + "MCH-save.txt";
        }
        return null; // Handle error (default file path maybe?)

    }

    public boolean createSaveFileIfNotExists(String filePath) {
        if (filePath != null) {
            File saveFile = new File(filePath);
            if (!saveFile.exists()) {
                try {
                    saveFile.createNewFile();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false; // Handle error (couldn't create file)
    }


}
