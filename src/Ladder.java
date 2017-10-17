import mayflower.Actor;

/**
 * Actor that character is able to "climb"; collision and climbing handled by client
 *
 * @author Shivashriganesh Mahato
 */
public class Ladder extends Actor {
    public void update() {
        setPicture("resources/sprites/Ladder.png");
    }
}
