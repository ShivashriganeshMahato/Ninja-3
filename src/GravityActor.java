import mayflower.Actor;

/**
 * Actor affected by a defined gravity. Collides with blocks
 *
 * @author Shivashriganesh Mahato
 */
public class GravityActor extends Actor {
    // This the constant gravitational acceleration exerted on the GravityActor
    protected final float Gravity = .25f;

    protected float vy;
    protected float yNxt;
    protected boolean isLanded;

    public GravityActor() {
        vy = 0;
        yNxt = 0;
        isLanded = false;
    }

    public void update() {
        // Vertical velocity is constantly influenced by gravity
        vy -= Gravity;
        // Get the y position of the GravityActor if it continues to move vertically, based on the current vertical velocity
        yNxt = getY() - vy;

        for (Actor actor : getStage().getActors()) {
            if (actor instanceof Block) {
                // Check if a block is in between the current and next y positions of the GravityActor
                if (vy < 0 && getY() + getHeight() / 2 <= actor.getY() - actor.getHeight() / 2 &&
                        yNxt + getHeight() / 2 >= actor.getY() - actor.getHeight() / 2 &&
                        getX() + getWidth() / 2 >= actor.getX() - actor.getWidth() / 2 &&
                        getX() - getWidth() / 2 <= actor.getX() + actor.getWidth() / 2) {
                    // If so, set isLanded to true (which will later set vy to 0) and move the GravityActor down the
                    // remaining distance from the Block. Also break out of the loop so isLanded isn't reset below
                    isLanded = true;
                    move(actor.getY() - (actor.getHeight() / 2) - getY() - (getHeight() / 2), "South");
                    break;
                }
                // Reset isLanded
                if (isLanded)
                    isLanded = false;
            }
        }

        // If the object is landed, its vertical velocity should be 0
        if (isLanded)
            vy = 0;
        // Move the GravityActor vertically based on vertical velocity
        move(vy, "North");
    }
}