package calendar.year._2018.day07;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Step implements Comparable<Step> {

    private String name;

    private List<String> previousSteps;

    public Step(String name) {
        this.name = name;
        this.previousSteps = new ArrayList<>();
    }

    @Override
    public int compareTo(Step otherStep) {
        return name.compareTo(otherStep.getName());
    }
}
