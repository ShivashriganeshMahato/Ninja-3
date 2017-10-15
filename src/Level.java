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
            "                    ",
            "                    ",
            "                    ",
            "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
        };
        
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
                }
            }
        }
        
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
