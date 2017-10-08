import mayflower.*;

/**
 * Write a description of class MovableAnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovableAnimatedActor extends AnimatedActor {
    private Animation run;
    
    private final float Max_SX = 5;
    private final float AxMax = .25f;
    private final float MaxJumpH = 165;
    private float vx;
    private float xNxt;
    private GravityActor dummy;
    
    public MovableAnimatedActor() {
        super();
       
        run = null;
        vx = 0;
        xNxt = 0;
        dummy = new GravityActor();
    }
    
    public void update() {
        super.update();

        Keyboard kb = getKeyboard();
        
        if (kb.isKeyPressed("←")) {
            vx = -Max_SX;
        } else if (kb.isKeyPressed("→")) {
            vx = Max_SX;
        } else {
            if (vx != 0) {
                vx -= Math.signum(vx) * AxMax;
            }
        }

        if (isLanded && kb.isKeyPressed("↑")) {
            isLanded = false;
            vy = (float) Math.sqrt(2 * Gravity * MaxJumpH);
        }

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
                System.out.println(getX());
                System.out.println(dummy.getX());
                System.out.println();
                if (vx > 0 && actor.isTouching(dummy)) {
                    vx = 0;
                }

//                if (vx < 0 && actor.isTouching((int) vx, 0, this)) {
//                    System.out.println(vx);
//                    vx = 0;
//                }
            }
        }
        
        move(vx, "east");
    }
    
    public void setRunAnimation(Animation ani) {
        run = ani;
    }
}
