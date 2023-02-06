package calendar.year._2022.day13;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Packet implements Value {

    private List<Value> values;

    public Packet() {
        this.values = new ArrayList<>();
    }

    public Packet(SingleValue singleValue) {
        this.values = Collections.singletonList(singleValue);
    }

    public Packet(Packet packet) {
        this.values = Collections.singletonList(packet);
    }

    @Override
    public int compareTo(Value o) {
        if (o instanceof Packet) {
            return this.compareTo((Packet) o);
        } else {
            return this.compareTo((SingleValue) o);
        }
    }

    private int compareTo(Packet o) {
        for (int i = 0; i < this.values.size(); i++) {
            Value left = this.values.get(i);
            Value right;
            try {
                right = o.values.get(i);
            } catch (IndexOutOfBoundsException e) {
                // If `this.values` has more elements than `o.values`, and if the previous elements are equivalents,
                // then `this` is considered greater
                return 1;
            }
            int valComparison = left.compareTo(right);
            if (0 != valComparison) {
                return valComparison;
            }
        }
        // If `this.values` has fewer elements than `o.values`, and if the previous elements are equivalents,
        // then `this` is considered smaller
        return this.values.size() < o.values.size() ? -1 : 0;
    }

    private int compareTo(SingleValue o) {
        if (this.values.isEmpty()) {
            return -1;
        } else {
            return this.compareTo(new Packet(o));
        }
    }

    @Override
    public String toString() {
        List<String> stringValues = values.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return "[" + String.join(",", stringValues) + "]";
    }
}
