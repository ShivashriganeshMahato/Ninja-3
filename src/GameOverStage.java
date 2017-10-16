import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameOverStage extends Stage {
    private Picture background;
    
    public GameOverStage() {
        background = new Picture("resources/sprites/GameOver.png");
        background.resize(800, 600);
    }
    
    public void update() {
        Keyboard kb = getKeyboard();

        setBackground(background);

        // Go back to the game on space press
        if (kb.isKeyPressed("space")) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameStage(1, 0));
        }
    }
}
