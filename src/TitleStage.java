import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class TitleStage extends Stage {
    private Picture background;
    
    public TitleStage() {
        background = new Picture("resources/sprites/Title.png");
        background.resize(800, 600);
    }
    
    public void update() {
        Keyboard kb = getKeyboard();
        
        setBackground(background);

        // ␣
        if (kb.isKeyPressed("space")) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameStage());
        }
    }
}
