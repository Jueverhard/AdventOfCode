package calendar.year._2023.day10;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PipeMaze extends Exercise {

    public PipeMaze(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Pipe> pipes = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int y = 0;
            while (null != (line = br.readLine())) {
                String finalLine = line;
                int finalY = y;
                Set<Pipe> linePipes = IntStream.range(0, line.length())
                        .mapToObj(x -> new Pipe(PipeKind.fromValue(finalLine.charAt(x)), new Position(x, finalY)))
                        .filter(pipe -> PipeKind.GROUND != pipe.pipeKind())
                        .collect(Collectors.toSet());
                pipes.addAll(linePipes);
                y++;
            }
        }

        Set<Pipe> visitedPipes = new HashSet<>();
        Set<Pipe> currentPipes = pipes.stream()
                .filter(pipe -> PipeKind.STARTING_POINT == pipe.pipeKind())
                .collect(Collectors.toSet());
        int nbSteps = 0;
        while (!currentPipes.isEmpty() && pipes.size() > 1) {
            Set<Pipe> nextPipes = pipes.parallelStream()
                    .filter(nextPipe -> !visitedPipes.contains(nextPipe))
                    .filter(nextPipe -> currentPipes.stream()
                            .anyMatch(currentPipe -> currentPipe.canConnectWith(nextPipe))
                    )
                    .collect(Collectors.toSet());
            visitedPipes.addAll(currentPipes);
            pipes.removeAll(currentPipes);
            currentPipes.clear();
            currentPipes.addAll(nextPipes);
            if (!nextPipes.isEmpty()) {
                nbSteps++;
            }
        }

        return print(nbSteps);
    }
}
