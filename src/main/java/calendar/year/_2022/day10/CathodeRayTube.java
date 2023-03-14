package calendar.year._2022.day10;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class CathodeRayTube extends Exercise {

    public CathodeRayTube(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Queue<Instruction> instructions = new ArrayDeque<>();

            // Read instructions
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineElements = line.split(" ");
                InstructionType instructionType = InstructionType.valueOf(lineElements[0].toUpperCase());
                int value = InstructionType.ADDX.equals(instructionType) ? Integer.parseInt(lineElements[1]) : 0;
                instructions.add(new Instruction(instructionType, value));
            }

            return Part.PART_1.equals(part) ? exercise1(instructions) : exercise2(instructions);
        }
    }

    private String exercise1(Queue<Instruction> instructions) {
        int sumOfSignals = 0;
        for (int i = 0; i < 6; i++) {
            int nthCycle = 20 + i * 40;
            sumOfSignals += computeSignalStrength(instructions, nthCycle, Part.PART_1) * nthCycle;
        }

        return print(sumOfSignals);
    }

    private String exercise2(Queue<Instruction> instructions) {
        List<Pixel> pixels = new ArrayList<>();

        // Defines pixels
        for (int i = 0; i < 240; i++) {
            PixelType pixelType = Math.abs(computeSignalStrength(instructions, i, Part.PART_2) - (i % 40)) <= 1 ?
                PixelType.LIT : PixelType.DARK;
            pixels.add(new Pixel(i, pixelType));
        }

        // Groups pixels by their line
        Map<Integer, List<Pixel>> pixelsPerLine = pixels.stream()
                .collect(Collectors.groupingBy(Pixel::getLine));

        StringBuilder res = new StringBuilder();
        // Print each line of pixels
        for (List<Pixel> pixelsLine : pixelsPerLine.values()) {
            String line = pixelsLine.stream()
                    .map(pixel -> pixel.getType().getRepresentation())
                    .map(String::valueOf)
                    .reduce(String::concat)
                    .orElseThrow();
            res.append(line).append("\n");
        }

        return print(res);
    }

    private int computeSignalStrength(final Queue<Instruction> instructions, int cycle, Part part) {
        Queue<Instruction> tmpInstructions = new ArrayDeque<>(instructions);
        int signalStrength = 1;
        int nbCycles = 0;
        while (nbCycles < cycle) {
            Instruction instruction = tmpInstructions.remove();
            nbCycles += instruction.getType().getNbCycles();
            if (Part.PART_1.equals(part) ? nbCycles >= cycle : nbCycles > cycle) {
                break;
            }
            signalStrength += instruction.getValue();
        }

        return signalStrength;
    }
}
