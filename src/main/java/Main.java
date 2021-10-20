import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<World.Point, World.Node> map = WorldReader.read();
        World world = new World(map);
        System.out.println(world.getIslandCount());
    }
}
