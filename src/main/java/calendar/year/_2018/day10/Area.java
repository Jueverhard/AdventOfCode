package calendar.year._2018.day10;

public record Area(int xMin, int xMax, int yMin, int yMax) {

    /**
     * @return The area surface.
     */
    public long computeSurface() {
        return Math.abs((long) xMax - xMin) * Math.abs(yMax - yMin);
    }
}
