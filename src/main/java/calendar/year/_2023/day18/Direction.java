package calendar.year._2023.day18;

public enum Direction {
    U, R, D, L;

    public boolean isVertical() {
        return U == this || D == this;
    }

    public boolean isHorizontal() {
        return L == this || R == this;
    }
}
