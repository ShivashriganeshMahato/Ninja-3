import mayflower.Actor;
import mayflower.Stage;
import mayflower.Text;

import java.awt.*;
import java.util.HashMap;

/**
 * Generates and manages game world based on inputted map
 *
 * @author Shivashriganesh Mahato
 */
public class Level {
    private Stage stage;
    private String[] map;
    private Ninja ninja;
    
    public Level(Stage stage, String[] map) {
        this.stage = stage;
        this.map = map;

        // Define a set of special items with corresponding points in the Stage. One will later be chosen at random to
        // actually be added to the world
        HashMap<Point, SpecialItem> specialItems = new HashMap<>();

        // Loop through each character in the level map and generate Actors accordingly
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                // Check what the character is at this point in the map, and add a specific Actor accordingly
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
                        // Instead of adding a SpecialItem to the Stage, it is added to the set of special items so one
                        // can be selected at random for actual entry into the Stage later
                        specialItems.put(new Point(c * 40 + 20, r * 40 + 20), new SpecialItem());
                        break;
                    case 'E':
                        stage.addActor(new EndPortal(), c * 40 + 20, r * 40 + 20);
                        break;
                }
            }
        }

        // Randomly select a location for the SpecialItem and add it to the Stage
        if (specialItems.size() > 0) {
            int SIInd = (int) Math.floor(Math.random() * specialItems.size());
            Point[] keys = specialItems.keySet().toArray(new Point[specialItems.size()]);
            stage.addActor(specialItems.get(keys[SIInd]), keys[SIInd].x, keys[SIInd].y);
        }

        // Add the ninja
        ninja = new Ninja(30);
        stage.addActor(ninja, 100, 0);
    }
    
    public void update() {
        // Move all Actors to the left, except for the Ninja and any HUD items. This gives the illusion that the screen
        // is scrolling
        for (Actor actor : stage.getActors()) {
            if (!(actor instanceof Ninja || actor instanceof Text || actor instanceof HudActor)) {
                actor.move(((GameStage) stage).getCurLevel(), "West");
                if (actor.getX() < -actor.getWidth() / 2)
                    stage.removeActor(actor);
            }
        }
    }
}
