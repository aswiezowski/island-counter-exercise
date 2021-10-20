import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class World {
    private Map<Point, Node> map;

    public World(Map<Point, Node> map) {
        this.map = map;
    }

    public int getIslandCount() {
        int islandCount = 0;

        for (Map.Entry<Point, Node> entry : map.entrySet()) {
            Point point = entry.getKey();
            Node node = entry.getValue();
            if (!node.isCounted()) {
                islandCount += 1;
                setIslandCounted(point);
            }
        }

        return islandCount;
    }

    private void setIslandCounted(Point point) {
        point.getAdjacentPoints()
                .forEach(adjacentPoint -> {
                    Node node = map.get(adjacentPoint);
                    if (node != null && !node.isCounted()) {
                        node.setCounted();
                        setIslandCounted(adjacentPoint);
                    }
                });
    }

    @Override
    public String toString() {
        return "World{" +
                "map=" + map +
                '}';
    }

    static class Point {
        private int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Set<Point> getAdjacentPoints() {
            return Set.of(
                    new Point(x - 1, y - 1),
                    new Point(x, y - 1),
                    new Point(x + 1, y - 1),
                    new Point(x - 1, y),
                    new Point(x + 1, y),
                    new Point(x - 1, y + 1),
                    new Point(x, y + 1),
                    new Point(x + 1, y + 1));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class Node {
        boolean isGround;
        boolean isCounted;

        Node(boolean isGround) {
            this.isGround = isGround;
            this.isCounted = false;
        }

        public boolean isGround() {
            return isGround;
        }

        public boolean isCounted() {
            return isCounted;
        }

        void setCounted() {
            isCounted = true;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "isGround=" + isGround +
                    ", isCounted=" + isCounted +
                    '}';
        }
    }
}
