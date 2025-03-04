package calendar.year._2024.day08;

public record Position(int x, int y) {
    
    public Position computeSymmetry(Position position) {
        int symmetricX = 2 * this.x - position.x();
        int symmetricY = 2 * this.y - position.y();
        return new Position(symmetricX, symmetricY);
    }

    public boolean isWithinBounds(int maxX, int maxY) {
        return x() >= 0 && x() <= maxX && y() >= 0 && y() <= maxY;
    }
}
