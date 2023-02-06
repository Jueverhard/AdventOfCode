package calendar.year._2022.day14;

public enum BlockType {
    ROCK('#'),
    SAND('o'),
    AIR('.');

    private char representation;

    BlockType(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }
}
