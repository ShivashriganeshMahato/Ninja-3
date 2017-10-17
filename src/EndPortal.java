import mayflower.Actor;
import mayflower.Mayflower;

/**
 * Entity at the end of the level. Upon contact with the character, the current level is complete
 *
 * @author Shivashriganesh Mahato
 */
public class EndPortal extends Actor {
    @Override
    public void update() {
        setPicture("resources/sprites/EndPortal.png");

        // Check for contact with level's Ninja
        for (Actor actor : getTouching()) {
            if (actor instanceof Ninja) {
                // Once contact has been made, go to the next level, inputting the score from the current level (a
                // property of GameStage)
                Mayflower mf = getMayflower();
                GameStage curStage = (GameStage) getStage();
                mf.setStage(new GameStage(curStage.getCurLevel() + 1, curStage.getScore()));
            }
        }
    }
}
