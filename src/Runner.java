import mayflower.*;

/**
 * Runs the game
 *
 * @author Shivashriganesh Mahato
 */
public class Runner {
    public static void main(String[] args) {
        // Start game with appropriate parameters (starts in title screen)
        new Mayflower("Ninja 3: Attack of the Samurai 8: Part 7", 800, 600, new TitleStage());
    }
}
