import mayflower.*;

/**
 * Write a description of class MovableAnimatedActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        isColliding = new boolean[]{false, false};
    }

    public void update() {
        super.update();

        Keyboard kb = getKeyboard();

        // ← → ↑
        if (kb.isKeyPressed("left")) {
            isColliding[0] = false;
            vx = -Max_SX;
            isLeft = true;
            if (isLanded && getAnimation() != runLeft)
                setAnimation(runLeft);
        } else if (kb.isKeyPressed("right")) {
            isColliding[1] = false;
            vx = Max_SX;
            isLeft = false;
            if (isLanded && getAnimation() != runRight)
                setAnimation(runRight);
        } else {
            if (vx != 0) {
                vx -= Math.signum(vx) * AxMax;
            }
            if (isLanded && getAnimation() != idleRight && getAnimation() != idleLeft)
                setAnimation(isLeft ? idleLeft : idleRight);
        }

        if (isLanded && kb.isKeyPressed("up")) {
            isLanded = false;
            vy = (float) Math.sqrt(2 * Gravity * MaxJumpH);
            if (getAnimation() != jumpRight && getAnimation() != jumpLeft)
                setAnimation(isLeft ? jumpLeft : jumpRight);
        }

        if (!isLanded && vy < 0 && getAnimation() != fallRight && getAnimation() != fallLeft)
            setAnimation(isLeft ? fallLeft : fallRight);

        dummy.setPosition(getX() + vx, getY());
        xNxt = getX() + vx;

        for (Actor actor : getStage().getActors()) {
            if (actor instanceof Block) {
                if (vy > 0 && getY() + vy - getHeight() / 2 >= actor.getY() + actor.getHeight() / 2 &&
                        yNxt - getHeight() / 2 <= actor.getY() + actor.getHeight() / 2 &&
                        getX() + getWidth() / 2 >= actor.getX() - actor.getWidth() / 2 &&
                        getX() - getWidth() / 2 <= actor.getX() + actor.getWidth() / 2) {
                    vy = 0;
                }

                if (!isColliding[0] && vx > 0 && isTouching((int) vx, 0, actor)) {
                    isColliding[0] = true;
                    move(actor.getX() - (actor.getWidth() / 2) - getX() - (getWidth() / 2), "East");
                    break;
                }
                if (isColliding[0]) {
                    isColliding[0] = false;
                }

                if (!isColliding[1] && vx < 0 && isTouching((int) vx, 0, actor)) {
                    isColliding[1] = true;
                    move(getX() - (getWidth() / 2) - actor.getX() - (actor.getWidth() / 2), "West");
                    break;
                }
                if (isColliding[1]) {
                    isColliding[1] = false;
                }
            }
            if (actor instanceof Ladder && isTouching(actor)) {
                if (kb.isKeyPressed("up")) {
                    vy = 3;
                    if (getAnimation() != runRight && getAnimation() != runLeft)
                        setAnimation(isLeft ? runLeft : runRight);
                }
            }
        }

        if (vx > 0 && xNxt + getWidth() / 2 > 800)
            vx = 0;

        if (isColliding[0] || isColliding[1])
            vx = 0;
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
