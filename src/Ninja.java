import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class Ninja extends MovableAnimatedActor {    
    public Ninja() {
        String[] runFileNames = new String[8];
        for (int i = 1; i <= 8; i++) {
            runFileNames[i - 1] = "resources/sprites/ninja/NinjaRun" + i + ".png";
        }
        
        Animation run = new Animation(60, runFileNames);
        run.resizeHeight(100);
        setAnimation(run);
    }
    
    public void update() {
        super.update();
    }
}