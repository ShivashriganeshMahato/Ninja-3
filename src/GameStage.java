import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    private Level curLevel;
    private Text lives;
    private Text scoreBoard;
    private Ninja gameChar;
    private HudActor redOverlay;
    
    public GameStage() {
        curLevel = new Level(this);
        lives = new Text("Lives: ");
        scoreBoard = new Text("Score: ");
        redOverlay = new HudActor("resources/sprites/Block.png");
        redOverlay.resize(800, 600);
        
        addActor(lives, 10, 10);
        addActor(scoreBoard, 10, 40);
        addActor(redOverlay, 400, 300);
    }
    
    public void update() {
        curLevel.update();
        
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
        scoreBoard.setText("Score: " + gameChar.getPoints());
    }

    public Ninja getGameChar() {
        return gameChar;
    }
}
