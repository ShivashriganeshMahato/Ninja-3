import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class Ninja extends MovableAnimatedActor {   
    private int lives;
    private int score;
    private Timer dmgTimer;
    private boolean isDamaged;
    
    public Ninja(int startingLives) {
        String[] runFileNames = new String[8];
        for (int i = 1; i <= 8; i++) {
            runFileNames[i - 1] = "resources/sprites/ninja/NinjaRun" + i + ".png";
        }
        
        Animation run = new Animation(60, runFileNames);
        run.resizeHeight(100);
        setRunAnimation(run);
        
        String[] idleFileNames = new String[3];
        for (int i = 1; i <= 3; i++) {
            idleFileNames[i - 1] = "resources/sprites/ninja/NinjaIdle" + i + ".png";
        }
        
        Animation idle = new Animation(200, idleFileNames);
        idle.resizeHeight(100);
        setIdleAnimation(idle);
        
        setAnimation(run);
        
        lives = startingLives;
        score = 0;
        dmgTimer = new Timer();
        isDamaged = false;
    }
    
    public void update() {
        super.update();
        
        if (isDamaged) {
            if (dmgTimer.hasTimePassed(1500)) {
                isDamaged = false;
                dmgTimer.reset();
            }
        } else {
            dmgTimer.reset();
        }
    }
    
    public void lowerLives(int amount) {
        if (!isDamaged) {
            lives -= amount;
            isDamaged = true;
        }
    }
    
    public int getLives() {
        return lives;
    }
    
    public void addPoints(int amount) {
        score += amount;
    }
    
    public int getPoints() {
        return score;
    }
    
    public int getTimePassed() {
        return dmgTimer.getTimePassed();
    }
    
    public boolean isDamaged() {
        return isDamaged;
    }
}