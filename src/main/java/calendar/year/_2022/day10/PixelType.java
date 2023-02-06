package calendar.year._2022.day10;

public enum PixelType {
    LIT('#'),
    DARK('.');

    private final char representation;

    PixelType(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }
}
