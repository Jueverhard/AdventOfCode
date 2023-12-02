package calendar.year._2023.day02;

public enum Color {
    RED, GREEN, BLUE;

    public static Color fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}
