package calendar.year._2022.day17;

public enum GasJet {
    LEFT, RIGHT;

    public static GasJet fromRepresentation(String representation) {
        switch (representation) {
            case "<":
                return GasJet.LEFT;
            case ">":
                return GasJet.RIGHT;
            default:
                throw new IllegalArgumentException("Illegal gas jet representation");
        }
    }
}
