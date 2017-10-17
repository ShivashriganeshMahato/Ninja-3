import mayflower.*;

import java.awt.*;

/**
 * Displays win screen (shows score)
 *
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

        // Go back to the game on space press (to play again)
        if (kb.isKeyPressed("space")) {
            Mayflower mf = getMayflower();
            mf.setStage(new GameStage(1, 0));
        }
    }
}
