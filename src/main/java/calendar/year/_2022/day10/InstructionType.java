package calendar.year._2022.day10;

public enum InstructionType {
    ADDX(2), NOOP(1);

    private final int nbCycles;

    InstructionType(int nbCycles) {
        this.nbCycles = nbCycles;
    }

    public int getNbCycles() {
        return nbCycles;
    }
}
