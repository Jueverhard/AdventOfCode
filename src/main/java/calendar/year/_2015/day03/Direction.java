package calendar.year._2015.day03;

public enum Direction {
    UP('^'),
    RIGHT('>'),
    DOWN('v'),
    LEFT('<');

    private final char representation;

    Direction(char representation) {
        this.representation = representation;
    }

    public static Direction fromValue(char representation) {
        for (Direction direction : values()) {
            if (direction.representation == representation) {
                return direction;
            }
        }
        throw new IllegalArgumentException("There is no Direction corresponding to " + representation);
    }
}
