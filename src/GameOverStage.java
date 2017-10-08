import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameOverStage extends Stage {
    private Picture background;
    
    public GameOverStage() {
        background = new Picture("sprites/GameOver.png");
        background.resize(800, 600);
    }
    
    public void update() {
        setBackground(background);
    }
}
