import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class HudActor extends Actor {
    private Picture pic;
    
    public HudActor(String picPath) {
        pic = new Picture(picPath);
    }
    
    public void update() {
        setPicture(pic);
    }
    
    public void resize(int w, int h) {
        pic.resize(w, h);
    }
    
    public void setTransparency(int transparency) {
        pic.setTransparency(transparency);
    }
}