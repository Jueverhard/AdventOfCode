package calendar.year._2017.day05;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AMazeOfTwistyTrampolines extends Exercise {
    public AMazeOfTwistyTrampolines(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Integer> input = Parser.parseLines(this.getInputPath(testMode)).stream()
                .map(Integer::parseInt)
                .toList();
        JumpInstructions instructions = Part.PART_1 == part ?
                new JumpInstructions(input) :
                new JumpInstructionsPart2(input);

        int nbInstructions = 0;
        while (instructions.executeInstruction()) {
            nbInstructions++;
        }

        return print(nbInstructions);
    }
}
