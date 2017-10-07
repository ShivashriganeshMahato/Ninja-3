import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    private Level curLevel;
    
    public GameStage() {
        curLevel = new Level(this);
    }
    
    public void update() {
    }
}
