import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GravityActor extends Actor {
    public void update() {
        if (isBlocked()) {
            move(5, "north");
        } else {
            move(5, "south");
        }
        
        for (Actor tActor : getTouching()) {
            if (tActor instanceof Ladder) {
                move(3, "north");
            }
        }
    }
    
    public boolean isBlocked() {
        Actor[] touchingActors = getTouching();
        boolean landed = false;
        
        for (Actor tActor : touchingActors) {
            if (tActor instanceof Block) {
                landed = true;
            }
        }
        
        return landed;
    }
    
    public boolean isFalling() {
        return !isBlocked();
    }
}