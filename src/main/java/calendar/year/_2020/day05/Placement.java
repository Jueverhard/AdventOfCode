package calendar.year._2020.day05;

public record Placement(int row, int column) {

    public int computeSeatId() {
        return row * 8 + column;
    }
}
