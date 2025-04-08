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

        // Cleans data from its ignored characters
        line = line.replaceAll("!.", "");

        // Cleans the garbage and computes its length
        int withGarbageLength = line.length();
        // Same as "<.*?>", but backtracking safe
        line = line.replaceAll("<[^>]++>", "<>");
        int garbageLength = withGarbageLength - line.length();

        // Suppresses the garbage markers
        line = line.replace("<>", "");

        // Extracts the groups from the cleaned line
        List<Group> groups = extractGroups(line);

        int result = Part.PART_1 == part ?
                groups.stream()
                        .mapToInt(group -> group.computeScore(1))
                        .sum() :
                garbageLength;

        return print(result);
    }

    /**
     * Recursively extracts the groups from the given line.
     *
     * @param line The line to extract groups from.
     * @return The extracted groups.
     */
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
                    groups.add(new Group(extractGroups(line.substring(start.getAsInt() + 1, i))));
                    start = OptionalInt.empty();
                }
            }
        }

        return groups;
    }
}
