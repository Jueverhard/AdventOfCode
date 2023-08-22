package calendar.year._2017.day05;

import java.util.List;

public class JumpInstructionsPart2 extends JumpInstructions {

    public JumpInstructionsPart2(final List<Integer> instructions) {
        super(instructions);
    }

    @Override
    protected int computeNewInstructionValue(int instruction) {
        return instruction >= 3 ? instruction - 1 : instruction + 1;
    }
}
