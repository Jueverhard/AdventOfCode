package calendar.year._2023.day08;

public record ReachableNode(String id, String left, String right) {

    public String getDestination(Direction direction) {
        return Direction.L == direction ? left : right;
    }
}
