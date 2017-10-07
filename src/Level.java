import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class Level {
    private String[] map;
    private Ninja ninja;
    
    public Level(Stage stage) {
        map = new String[] {
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "          L         ",
            "          L         ",
            "          L         ",
            "          L SSSSSSSS",
            "          L         ",
            "          L         ",
            "          L         ",
            "          L         ",
            "SSSSSSSSSSSSSSSSSSSS"
        };
        
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                if (map[r].charAt(c) == 'S') {
                    stage.addActor(new Block(), c * 40 + 20, r * 40 + 20);
                } else if (map[r].charAt(c) == 'L') {
                    stage.addActor(new Ladder(), c * 40 + 20, r * 40 + 20);
                }
            }
        }
        
        ninja = new Ninja();
        stage.addActor(ninja, 100, 500);
    }
}
