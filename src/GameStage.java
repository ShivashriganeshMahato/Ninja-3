import mayflower.*;

import java.awt.*;

/**
 * Manages game screen, based on inputted level and starting score
 *
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    // Level maps
    private final String[][] lvlMaps = {
            {
                    "                                             X                                          ",
                    "                                                                                        ",
                    "                                        BB                                              ",
                    "                                   X                                                    ",
                    "                    BB            BBB                                                   ",
                    "                                                                                        ",
                    "                                                                                        ",
                    "BBBBBBBBBB      BB                                                          B B         ",
                    "                                            SS      SS                    B B B       E ",
                    "           BB                              BBBBBBBBBBBBBB               B B B B      BBB",
                    "                                 K    BB                  S           B B B B B         ",
                    "                        BB      BBB                      BBBBB      B B B B B B         ",
                    "                                                                  B B B B B B B         ",
                    "                                                               BB B B B B B B B         ",
                    "                                                                                        "
            },
            {
                    "                                                                                                                                      ",
                    "                                                                                                                                      ",
                    "                                                                                       T                                              ",
                    "                                                                                                                                      ",
                    "                                                                                                                                      ",
                    "                                                                                        K    BB                                       ",
                    "                                                                                     K BBBB      B   B   K  K                         ",
                    "                T                              T                          S   BBBBBB B                   B  B  B                      ",
                    "                                                                        BBBB                                                          ",
                    "                           X                                        BBB          T                                 BBBB  S            ",
                    "                L      B  BBB                X   S        BBBBBBBB                                                      BBB S         ",
                    "        K  T    K    B          S K         BBBBBBBBBBBB                                                                   BBB        ",
                    "     BBBBBB        B          BBBBB        L                                                                                  BB      ",
                    "            BBBBB                    BBBBBBB                                                                                     BB  E",
                    "                                                                                                                                    BB"
            },
            {
                    "                                                    T                                                                                                                                              ",
                    "                              X                         K                                         X                                                                                                ",
                    "                                              K        BBB                                                                                                                     T          T        ",
                    "                                             BBBL                                                                                                                                                  ",
                    "                 T                                                                                                                                   L                             S K S           ",
                    "                                                                   KK                                                  T  T  T  T  T  T  T           L  BBB            BBBB       BBBBBBB          ",
                    "                                     K                         BBBBBBB                                    S                                          L                                             ",
                    "BBBBBBBBBBBBBBBB                    BBBB           K                                                   BBBBBB                                        L           BBBB                            E ",
                    "                    SSSSSSSS    S                 BBB                     SSS   SSS   SSS   SSS                                                      L                               T          BBB",
                    "                  BBBBBBBBBBBBBBBB  T        K                         BBBBBBBBBBBBBBBBBBBBBBBBBBBB                                           K      L                                             ",
                    "                                            BBB         T                                                            BBBBBBBBBBBBBBBBBBBBBBBBBB      L                                             ",
                    "                                                                                                                                                     L                                             ",
                    "                                                                                                                                                     L                                             ",
                    "                                                                                                                                                    XL                                             ",
                    "                                                                                                                                                    B                                              "
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
    private boolean isWon;

    public GameStage(int level, int curScore) {
        // Create a new level passing in the level map corresponding with the current level
        this.curLevel = level;
        // If the inputted level doesn't exist, the game is won
        try {
            this.level = new Level(this, lvlMaps[curLevel - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            isWon = true;
        }
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
        // Flag is true only when inputted level doesn't exist. In this case, go to win screen and exit method
        if (isWon) {
            Mayflower mf = getMayflower();
            mf.setStage(new WinStage(score));
            return;
        }

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

        // If the player's lives reach 0, the game is over
        if (gameChar.getLives() <= 0) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameOverStage());
        }

        // If the player falls off the screen, reduce health and respawn at the top
        if (gameChar.getY() > 700) {
            gameChar.lowerLives(2);
            gameChar.setPosition(gameChar.getX(), 0);
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
