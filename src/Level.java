import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class Level {
    private Stage stage;
    private String[] map;
    private Ninja ninja;
    
    public Level(Stage stage) {
        this.stage = stage;
        
        map = new String[] {
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                T   ",
            "                    ",
            "                 L  ",
            "          B      L  ",
            "              K     ",
            "          BBBBB     ",
            "          BBBBB     ",
            "    B  S  BBBBB     ",
            "BBBB BBBBBBBBBBBBBBB"
        };
        
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                if (map[r].charAt(c) == 'B') {
                    stage.addActor(new Block(), c * 40 + 20, r * 40 + 20);
                } else if (map[r].charAt(c) == 'S') {
                    stage.addActor(new Spike(), c * 40 + 20, r * 40 + 20);
                } else if (map[r].charAt(c) == 'K') {
                    stage.addActor(new KitKat(), c * 40 + 20, r * 40 + 20);
                } else if (map[r].charAt(c) == 'L') {
                    stage.addActor(new Ladder(), c * 40 + 20, r * 40 + 20);
                } else if (map[r].charAt(c) == 'T') {
                    stage.addActor(new Turret(), c * 40 + 20, r * 40 + 20);
                }
            }
        }
        
        ninja = new Ninja(3);
        stage.addActor(ninja, 100, 0);
    }
    
    public void update() {
        for (Actor actor : stage.getActors()) {
            if (!(actor instanceof Ninja || actor instanceof Text || actor instanceof HudActor)) {
//                actor.move(1, "West");
            }
        }
    }
}
