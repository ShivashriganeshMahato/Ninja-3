import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
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
    
    public GameStage(int level, int curScore) {
        this.curLevel = level;
        this.level = new Level(this, lvlMaps[curLevel - 1]);
        score = curScore;

        lives = new Text("Lives: ");
        scoreBoard = new Text("Score: ");
        levelDisp = new Text("Level " + curLevel);
        redOverlay = new HudActor("resources/sprites/Block.png");
        redOverlay.resize(800, 600);
        
        addActor(lives, 10, 10);
        addActor(scoreBoard, 10, 40);
        addActor(redOverlay, 400, 300);
        addActor(levelDisp, 700, 10);
    }
    
    public void update() {
        level.update();
        
        if (gameChar == null) {
            for (Actor actor : getActors()) {
                if (actor instanceof Ninja) {
                    gameChar = (Ninja) actor;
                }
            }
        }
        
        if (gameChar.getLives() <= 0 || gameChar.getY() > 700) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameOverStage());
        }
        
        if (gameChar.isDamaged()) {
            redOverlay.setTransparency((int) (((gameChar.getTimePassed() / 1500.0) * 50) + 50));
        } else {
            redOverlay.setTransparency(100);
        }
        
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
