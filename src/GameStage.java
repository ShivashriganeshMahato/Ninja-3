import mayflower.*;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    // Level maps
    private final String[][] lvlMaps = {
            {
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                T   ",
                    "     X X  X    X X  ",
                    "                 L  ",
                    "          B      L  ",
                    "              K     ",
                    "                    ",
                    "                    ",
                    "                    E",
                    "BBBBBBB    BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
            },
            {
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                T   ",
                    "     X X  X    X X  ",
                    "                 L  ",
                    "          B      L  ",
                    "              K     ",
                    "      BBBBB            ",
                    "                    ",
                    "                    E",
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
            },
            {
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                    ",
                    "                T   ",
                    "     X X  X    X X  ",
                    "                 L  ",
                    "        T B      L  ",
                    "      X       K     ",
                    "        T     T     ",
                    "                    ",
                    "            T       E",
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
            }
    };

    private Level level;
    private Text lives;
    private Text scoreBoard;
    private Text levelDisp;
    private Ninja gameChar;
    private HudActor redOverlay;
    private int curLevel;
    private int score;
    private Picture background;
    
    public GameStage(int level, int curScore) {
        // Create a new level passing in the level map corresponding with the current level
        this.curLevel = level;
        this.level = new Level(this, lvlMaps[curLevel - 1]);
        score = curScore;

        lives = new Text("Lives: ");
        lives.setColor(Color.WHITE);
        scoreBoard = new Text("Score: ");
        scoreBoard.setColor(Color.WHITE);
        levelDisp = new Text("Level " + curLevel);
        levelDisp.setColor(Color.WHITE);
        redOverlay = new HudActor("resources/sprites/RedOverlay.png");
        redOverlay.resize(800, 600);
        
        addActor(lives, 10, 10);
        addActor(scoreBoard, 10, 40);
        addActor(redOverlay, 400, 300);
        addActor(levelDisp, 700, 10);

        background = new Picture("resources/sprites/Background.jpg");
        background.resize(800, 600);
    }
    
    public void update() {
        setBackground(background);

        level.update();

        // Get a reference to the level's Ninja (stored in gameChar)
        if (gameChar == null) {
            for (Actor actor : getActors()) {
                if (actor instanceof Ninja) {
                    gameChar = (Ninja) actor;
                }
            }
        }

        // If the player's lives reach 0 or the player falls off, the game is over
        if (gameChar.getLives() <= 0 || gameChar.getY() > 700) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameOverStage());
        }

        // When the player is damaged, fade out a red overlay to indicate invulnerability. While the overlay is fading
        // out, the player is invulnerable to damage. After it has faded out, the player can be damaged again
        if (gameChar.isDamaged()) {
            redOverlay.setTransparency((int) (((gameChar.getTimePassed() / 1500.0) * 50) + 50));
        } else {
            redOverlay.setTransparency(100);
        }

        // Display current lives and score of player
        lives.setText("Lives: " + gameChar.getLives());
        scoreBoard.setText("Score: " + (score + gameChar.getPoints()));
    }

    public Ninja getGameChar() {
        return gameChar;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public int getScore() {
        return score + gameChar.getPoints();
    }
}
