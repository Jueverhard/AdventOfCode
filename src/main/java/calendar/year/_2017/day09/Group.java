package calendar.year._2017.day09;

import java.util.List;

public record Group(List<Group> groups, List<Garbage> garbages) {

    int computeScore(int depth) {
        return depth + groups.stream()
                .mapToInt(group -> group.computeScore(depth + 1))
                .sum();
    }
}
