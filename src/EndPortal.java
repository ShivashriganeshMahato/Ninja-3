import mayflower.Actor;
import mayflower.Mayflower;

/**
 * @author Shivashriganesh Mahato
 */
public class EndPortal extends Actor {
    @Override
    public void update() {
        setPicture("resources/sprites/EndPortal.png");

        for (Actor actor : getTouching()) {
            if (actor instanceof Ninja) {
                Mayflower mf = getMayflower();
                GameStage curStage = (GameStage) getStage();
                if (curStage.getCurLevel() == 3)
                    mf.setStage(new WinStage(curStage.getScore()));
                else
                    mf.setStage(new GameStage(curStage.getCurLevel() + 1, curStage.getScore()));
            }
        }
    }
}
