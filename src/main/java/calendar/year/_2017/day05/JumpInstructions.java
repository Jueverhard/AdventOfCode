package calendar.year._2017.day05;

import utils.enums.Part;

import java.util.ArrayList;
import java.util.List;

public class JumpInstructions {

    private int currentIndex;

    private final List<Integer> instructions;

    private final Part part;

    public JumpInstructions(final List<Integer> instructions, Part part) {
        this.instructions = new ArrayList<>(instructions);
        this.part = part;
        this.currentIndex = 0;
    }

    /**
     * Execute the ongoing instruction
     *
     * @return true if there is still an instruction to execute
     */
    public boolean executeInstruction() {
        int instruction;
        try {
            instruction = instructions.get(currentIndex);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        instructions.set(currentIndex, computeNewInstructionValue(instruction));
        currentIndex += instruction;
        return currentIndex >= 0 && currentIndex <= instructions.size();
    }

    /**
     * @param instruction The instruction to update
     * @return the value to which the instruction should be updated after execution
     */
    private int computeNewInstructionValue(int instruction) {
        if (Part.PART_1 == part) {
            return instruction + 1;
        } else {
            return instruction >= 3 ? instruction - 1 : instruction + 1;
        }
    }
}
