import mayflower.Actor;
import mayflower.Stage;
import mayflower.Text;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Shivashriganesh Mahato
 */
public class Level {
    private Stage stage;
    private String[] map;
    private Ninja ninja;
    
    public Level(Stage stage, String[] map) {
        this.stage = stage;
        this.map = map;

        HashMap<Point, SpecialItem> specialItems = new HashMap<>();
        
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                switch (map[r].charAt(c)) {
                    case 'B':
                        stage.addActor(new Block(), c * 40 + 20, r * 40 + 20);
                        break;
                    case 'S':
                        stage.addActor(new Spike(), c * 40 + 20, r * 40 + 20);
                        break;
                    case 'K':
                        stage.addActor(new KitKat(), c * 40 + 20, r * 40 + 20);
                        break;
                    case 'L':
                        stage.addActor(new Ladder(), c * 40 + 20, r * 40 + 20);
                        break;
                    case 'T':
                        stage.addActor(new Turret(), c * 40 + 20, r * 40 + 20);
                        break;
                    case 'X':
                        specialItems.put(new Point(c * 40 + 20, r * 40 + 20), new SpecialItem());
                        break;
                    case 'E':
                        stage.addActor(new EndPortal(), c * 40 + 20, r * 40 + 20);
                        break;
                }
            }
        }

        int SIInd = (int) Math.floor(Math.random() * specialItems.size());
        Point[] keys = specialItems.keySet().toArray(new Point[specialItems.size()]);
        stage.addActor(specialItems.get(keys[SIInd]), keys[SIInd].x, keys[SIInd].y);
        
        ninja = new Ninja(3);
        stage.addActor(ninja, 100, 0);
    }
    
    public void update() {
        for (Actor actor : stage.getActors()) {
            if (!(actor instanceof Ninja || actor instanceof Text || actor instanceof HudActor)) {
                actor.move(1, "West");
                if (actor.getX() < -actor.getWidth() / 2)
                    stage.removeActor(actor);
            }
        }

        System.out.println(stage.getActors().length);
    }
}
