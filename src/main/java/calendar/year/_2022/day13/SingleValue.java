package calendar.year._2022.day13;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SingleValue implements Value {

    private int value;

    @Override
    public int compareTo(Value o) {
        if (o instanceof Packet) {
            return this.compareTo((Packet) o);
        } else {
            return this.compareTo((SingleValue) o);
        }
    }

    private int compareTo(Packet o) {
        if (o.getValues().isEmpty()) {
            return 1;
        }
        return new Packet(this).compareTo(o);
    }

    private int compareTo(SingleValue o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
