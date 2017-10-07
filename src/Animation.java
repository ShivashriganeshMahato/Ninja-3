import mayflower.*;

/** 
 * @author Shivashriganesh Mahato
 */
public class Animation {
    private Picture[] frames;
    private int frameRate;
    private int currentFrame;
    
    public Animation(int frameRate, String[] images) {
        this.frameRate = frameRate;
        frames = new Picture[images.length];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new Picture(images[i]);
        }
    }
    
    public int getFrameRate() {
        return frameRate;
    }
    
    public Picture getNextFrame() {
        Picture curFrame = frames[currentFrame];
        currentFrame = (currentFrame + 1) % frames.length;
        return curFrame;
    }
    
    public void resize(int w, int h) {
        for (Picture frame : frames) {
            frame.resize(w, h);
        }
    }
    
    public void resizeHeight(int h) {
        for (Picture frame : frames) {
            int nW = (frame.getWidth() * h / frame.getHeight());
            frame.resize(nW, h);
        }
    }
    
    public void setTransparency(int percent) {
        for (Picture frame : frames) {
            frame.setTransparency(percent);
        }
    }
    
    public void flipHorizontal() {
        for (Picture frame : frames) {
            frame.flipHorizontal();
        }
    }
    
    public void setBounds(int x, int y, int w, int h) {
        for (Picture frame : frames) {
            frame.setBounds(x, y, w, h);
        }
    }
}