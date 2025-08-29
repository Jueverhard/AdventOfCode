package calendar.year._2016.day10;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class Output implements Target {

    private final int id;

    @Setter
    private Integer value;

    @Override
    public Optional<Integer> addValue(int value) {
        this.value = value;
        return Optional.empty();
    }
}
