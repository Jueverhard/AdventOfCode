package calendar.year._2018.day05;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class AlchemicalReduction extends Exercise {
    public AlchemicalReduction(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Polymer polymer;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            polymer = new Polymer(br.readLine());
        }

        String finalPolymer = Part.PART_1 == part ?
                polymer.computeFullyReactedChain() :
                polymer.computeShortestFullyReactedChain();
        return print(finalPolymer.length());
    }
}
