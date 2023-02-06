package calendar.year._2022.day15;

public enum Spot {
    SENSOR('S'),
    BEACON('B'),
    NO_BEACON('#'),
    UNKNOWN('.');

    private char representation;

    Spot(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }
}
