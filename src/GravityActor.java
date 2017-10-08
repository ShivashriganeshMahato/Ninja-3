import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GravityActor extends Actor {
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
        vy -= Gravity;
        yNxt = getY() - vy;

        for (Actor actor : getStage().getActors()) {
            if (actor instanceof Block) {
                if (vy < 0 && getY() + getHeight() / 2 <= actor.getY() - actor.getHeight() / 2 &&
                        yNxt + getHeight() / 2 >= actor.getY() - actor.getHeight() / 2 &&
                        getX() + getWidth() / 2 >= actor.getX() - actor.getWidth() / 2 &&
                        getX() - getWidth() / 2 <= actor.getX() + actor.getWidth() / 2) {
                    isLanded = true;
                    move(actor.getY() - (actor.getHeight() / 2) - getY() - (getHeight() / 2), "South");
                    break;
                }
                if (isLanded)
                    isLanded = false;
            }
        }

        if (isLanded)
            vy = 0;
        move(vy, "North");
    }

    public boolean isToBeBlocked() {
        Actor[] touchingActors = getTouching();
        boolean landed = false;

        for (Actor tActor : touchingActors) {
            if (tActor instanceof Block) {
                landed = true;
            }
        }

        return landed;
    }
    
    /*public boolean isFalling() {
        return !isBlocked();
    }*/
}