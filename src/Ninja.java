import mayflower.Timer;

/**
 * Main game Actor. It is a MovableAnimatedActor that has properties of a ninja (appropriate animations, temporary
 * invulnerability if hit, etc.)
 *
 * @author Shivashriganesh Mahato, Sugam Arora
 */
public class Ninja extends MovableAnimatedActor {
    private int lives;
    private int score;
    private Timer dmgTimer;
    private boolean isDamaged;

    public Ninja(int startingLives) {
        // Initialize animations with appropriate sprite sets

        String[] runFileNames = new String[8];
        for (int i = 1; i <= 8; i++) {
            runFileNames[i - 1] = "resources/sprites/ninja/NinjaRun" + i + ".png";
        }

        Animation runRight = new Animation(60, runFileNames);
        runRight.resizeHeight(100);
        setRunRightAnimation(runRight);

        Animation runLeft = new Animation(60, runFileNames);
        runLeft.resizeHeight(100);
        runLeft.flipHorizontal();
        setRunLeftAnimation(runLeft);

        String[] idleFileNames = new String[3];
        for (int i = 1; i <= 3; i++) {
            idleFileNames[i - 1] = "resources/sprites/ninja/NinjaIdle" + i + ".png";
        }

        Animation idleRight = new Animation(200, idleFileNames);
        idleRight.resizeHeight(100);
        setIdleRightAnimation(idleRight);

        Animation idleLeft = new Animation(200, idleFileNames);
        idleLeft.resizeHeight(100);
        idleLeft.flipHorizontal();
        setIdleLeftAnimation(idleLeft);

        String[] jumpFileNames = new String[3];
        for (int i = 1; i <= 3; i++) {
            jumpFileNames[i - 1] = "resources/sprites/ninja/NinjaJump" + i + ".png";
        }

        Animation jumpRight = new Animation(120, jumpFileNames);
        jumpRight.resizeHeight(100);
        setJumpRightAnimation(jumpRight);

        Animation jumpLeft = new Animation(120, jumpFileNames);
        jumpLeft.resizeHeight(100);
        jumpLeft.flipHorizontal();
        setJumpLeftAnimation(jumpLeft);

        String[] fallFileNames = {"resources/sprites/ninja/NinjaFall.png"};

        Animation fallRight = new Animation(1, fallFileNames);
        fallRight.resizeHeight(100);
        setFallRightAnimation(fallRight);

        Animation fallLeft = new Animation(1, fallFileNames);
        fallLeft.resizeHeight(100);
        fallLeft.flipHorizontal();
        setFallLeftAnimation(fallLeft);

        setAnimation(idleRight);

        lives = startingLives;
        score = 0;
        dmgTimer = new Timer();
        isDamaged = false;
    }

    public void update() {
        super.update();

        // The damage timer only increments if isDamaged is true. Once it goes up to 1.5s, isDamaged is set to false and
        // it continuously resets every frame until damage occurs again. This makes the invulnerability feature
        // possible: if the character is damaged by some harmful entity, it cannot be damaged again until this timer
        // reaches the threshold of 1.5s. That way damage cannot be done simultaneously. The indicator of the timer
        // for the player is the red fading overlay. Once it has faded out (after 1.5s), the player is vulnerable again
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
        // As long as the player is vulnerable, apply the damage
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