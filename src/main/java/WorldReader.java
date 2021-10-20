import java.util.HashMap;
import java.util.Scanner;

public class WorldReader {

    static HashMap<World.Point, World.Node> read() {
        Scanner scanner = new Scanner(System.in);
        int yPosition = 0;

        HashMap<World.Point, World.Node> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int charPosition = 0, xPosition = 0; charPosition < line.length(); charPosition += 2, xPosition += 1) {
                if (line.charAt(charPosition) == '1') {
                    map.put(new World.Point(xPosition, yPosition), new World.Node(true));
                }
            }
            yPosition += 1;
        }
        return map;
    }
}
