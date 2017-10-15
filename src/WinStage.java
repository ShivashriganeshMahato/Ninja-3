import mayflower.*;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class WinStage extends Stage {
    private Picture background;
    private Text text;

    public WinStage(int score) {
        text = new Text("Your score is " + score);
        text.setColor(Color.WHITE);
        addActor(text, 500, 25);
        background = new Picture("resources/sprites/WinScreen.jpg");
        background.resize(800, 600);
    }

    public void update() {
        Keyboard kb = getKeyboard();

        setBackground(background);

        if (kb.isKeyPressed("space")) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameStage(1, 0));
        }
    }
}
