package calendar.year._2016.day02;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Row {

    private final List<Character> chars;

    public char getKey(int index) {
        return chars.get(index);
    }
}
