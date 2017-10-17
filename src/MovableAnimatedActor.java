import mayflower.*;

/**
 * AnimatedActor that is able to move horizontally and jump
 *
 * @author Shivashriganesh Mahato
 */
public class MovableAnimatedActor extends AnimatedActor {
    private Animation idleRight;
    private Animation idleLeft;
    private Animation runRight;
    private Animation runLeft;
    private Animation jumpRight;
    private Animation jumpLeft;
    private Animation fallRight;
    private Animation fallLeft;
    private boolean isLeft;

    private final float Max_SX = 5;
    private final float AxMax = .25f;
    private final float MaxJumpH = 165;
    private float vx;
    private float xNxt;
    private GravityActor dummy;
    private boolean[] isColliding;

    public MovableAnimatedActor() {
        super();

        runRight = runLeft = idleRight = idleLeft = jumpRight = jumpLeft = fallRight = fallLeft = null;
        isLeft = false;
        vx = 0;
        xNxt = 0;
        dummy = new GravityActor();
        // First element is a flag for right collision, second for left collision
        isColliding = new boolean[]{false, false};
    }

    public void update() {
        super.update();

        int Right = 0, Left = 1; // Indexes for collision flags (for convenience)

        Keyboard kb = getKeyboard();

        // Handle horizontal movement
        if (kb.isKeyPressed("left")) {
            // Set right collision flag to true so player doesn't get stuck after a right collision
            isColliding[Right] = false;
            // Make velocity negative so player moves to left
            vx = -Max_SX;
            isLeft = true;
            if (isLanded && getAnimation() != runLeft)
                setAnimation(runLeft);
        } else if (kb.isKeyPressed("right")) {
            // Set left collision flag to true so player doesn't get stuck after a left collision
            isColliding[Left] = false;
            // Make velocity positive so player moves to right
            vx = Max_SX;
            isLeft = false;
            if (isLanded && getAnimation() != runRight)
                setAnimation(runRight);
        } else {
            // If the player stops pressing right and left, bring the Actor to a stop
            if (vx != 0) {
                vx -= Math.signum(vx) * AxMax;
            }
            // Set idle animation based on direction from movement
            if (isLanded && getAnimation() != idleRight && getAnimation() != idleLeft)
                setAnimation(isLeft ? idleLeft : idleRight);
        }

        // Handle jumping
        if (isLanded && kb.isKeyPressed("up")) {
            isLanded = false;
            // Initial vertical velocity calculated based on desired max height (MaxJumpH)
            vy = (float) Math.sqrt(2 * Gravity * MaxJumpH);
            // Set jump animation based on current direction
            if (getAnimation() != jumpRight && getAnimation() != jumpLeft)
                setAnimation(isLeft ? jumpLeft : jumpRight);
        }

        // Set fall animation based on current direction
        if (!isLanded && vy < 0 && getAnimation() != fallRight && getAnimation() != fallLeft)
            setAnimation(isLeft ? fallLeft : fallRight);

        // Update dummy and next x position
        dummy.setPosition(getX() + vx, getY());
        xNxt = getX() + vx;

        for (Actor actor : getStage().getActors()) {
            if (actor instanceof Block) {
                // If the Actor is jumping and will come in contact with the bottom of a block if it continues moving,
                // stop it vertically and move it the remaining distance from the bottom
                if (vy > 0 && getY() + vy - getHeight() / 2 >= actor.getY() + actor.getHeight() / 2 &&
                        yNxt - getHeight() / 2 <= actor.getY() + actor.getHeight() / 2 &&
                        getX() + getWidth() / 2 >= actor.getX() - actor.getWidth() / 2 &&
                        getX() - getWidth() / 2 <= actor.getX() + actor.getWidth() / 2) {
                    vy = 0;
                }

                // Check if Actor will collide to the left of a block by testing collision of dummy (set at the next x
                // position). If so, set right collision flag to true (which will later stop movement) and move Actor
                // the remaining distance from the left of the block. Also break loop so flag reset won't occur below
                if (!isColliding[Right] && vx > 0 && isTouching((int) vx, 0, actor)) {
                    isColliding[Right] = true;
                    move(actor.getX() - (actor.getWidth() / 2) - getX() - (getWidth() / 2), "East");
                    break;
                }
                // Reset right collision flag if there is no collision
                if (isColliding[Right]) {
                    isColliding[Right] = false;
                }

                // Do the same thing as above, but for left collision by checking for dummy collision with the right
                // side of a block
                if (!isColliding[Left] && vx < 0 && isTouching((int) vx, 0, actor)) {
                    isColliding[Left] = true;
                    move(getX() - (getWidth() / 2) - actor.getX() - (actor.getWidth() / 2), "West");
                    break;
                }
                if (isColliding[Left]) {
                    isColliding[Left] = false;
                }
            }

            // Move Actor up and show running animation if in contact with a ladder and pressing up
            if (actor instanceof Ladder && isTouching(actor)) {
                if (kb.isKeyPressed("up")) {
                    vy = 3;
                    if (getAnimation() != runRight && getAnimation() != runLeft)
                        setAnimation(isLeft ? runLeft : runRight);
                }
            }
        }

        // Stop Actor from moving too far right of screen
        if (vx > 0 && xNxt + getWidth() / 2 > 800)
            vx = 0;

        // If a collision was detected, stop horizontal movement
        if (isColliding[Right] || isColliding[Left])
            vx = 0;

        // Move horizontally if there is a horizontal velocity
        move(vx, "east");
    }

    public void setRunRightAnimation(Animation ani) {
        runRight = ani;
    }

    public void setRunLeftAnimation(Animation ani) {
        runLeft = ani;
    }
    
    public void setIdleRightAnimation(Animation ani) {
        idleRight = ani;
    }

    public void setIdleLeftAnimation(Animation ani) {
        idleLeft = ani;
    }

    public void setJumpRightAnimation(Animation ani) {
        jumpRight = ani;
    }

    public void setJumpLeftAnimation(Animation ani) {
        jumpLeft = ani;
    }

    public void setFallRightAnimation(Animation ani) {
        fallRight = ani;
    }

    public void setFallLeftAnimation(Animation ani) {
        fallLeft = ani;
    }
}
