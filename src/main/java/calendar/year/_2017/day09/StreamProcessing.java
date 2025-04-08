package calendar.year._2017.day09;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;

public class StreamProcessing extends Exercise {

    public StreamProcessing(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        String line = Parser.parseLines(this.getInputPath(testMode)).get(0);
        line = line.replaceAll("!.", "");
        line = line.replaceAll("<.*?>", "");
        List<Group> groups = extractGroups(line);


        int result = groups.stream()
                .mapToInt(group -> group.computeScore(1))
                .sum();

        return print(result);
    }

    private List<Group> extractGroups(String line) {
        if (line.isEmpty()) {
            return Collections.emptyList();
        }

        List<Group> groups = new ArrayList<>();
        int depth = 0;
        OptionalInt start = OptionalInt.empty();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                depth++;
                if (start.isEmpty()) {
                    start = OptionalInt.of(i);
                }
            } else if (line.charAt(i) == '}') {
                depth--;
                if (depth == 0 && start.isPresent()) {
                    groups.add(new Group(
                            extractGroups(line.substring(start.getAsInt() + 1, i)),
                            Collections.emptyList()
                    ));
                    start = OptionalInt.empty();
                }
            }
        }

        return groups;
    }
}
