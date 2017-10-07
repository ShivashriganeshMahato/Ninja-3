import mayflower.*;
import java.lang.reflect.*;

/**
 * Write a description of class MovableAnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovableAnimatedActor extends AnimatedActor {
    private Animation run;
    
    private final float max_sx = 5;
    private final float axMax = .25f;
    private float vx;
    
    public MovableAnimatedActor() {
        super();
       
        run = null;
        vx = 0;
    }
    
    public void update() {
        super.update();
        
        Keyboard kb = getKeyboard();
        
        if (kb.isKeyPressed("left")) {
            vx = -max_sx;
        } else if (kb.isKeyPressed("right")) {
            vx = max_sx;
        } else {
            if (vx != 0) {
                vx -= Math.signum(vx) * axMax;
            }
        }
        
        move(vx, "east");
    }
    
    public void setRunAnimation(Animation ani) {
        run = ani;
    }
}
