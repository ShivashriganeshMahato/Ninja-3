import mayflower.*;

/**
 * An Actor affected by gravity that plays an animation as its sprite
 *
 * @author Shivashriganesh Mahato
 */

public class AnimatedActor extends GravityActor {
    private Animation animation;
    private Timer animationTimer;

    public AnimatedActor() {
        animation = null;
        animationTimer = new Timer();
    }

    public void setAnimation(Animation a) {
        animation = a;
        animation.reset();
        animationTimer.reset();
    }

    public Animation getAnimation() {
        return animation;
    }

    public void update() {
        super.update();

        if (animation != null) {
            // If the timer reaches a threshold, update the animation to the next frame
            if (animationTimer.hasTimePassed(animation.getFrameRate())) {
                animationTimer.reset();
                setPicture(animation.getNextFrame());
            }
        }
    }
}