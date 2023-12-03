package calendar.year._2023.day02;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CubeConundrum extends Exercise {

    public CubeConundrum(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Game> games = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                games.add(new Game(line));
            }
        }

        int res;
        if (Part.PART_1 == part) {
            res = games.stream()
                    .filter(Game::isLegit)
                    .map(Game::getId)
                    .reduce(0, Integer::sum);
        } else {
            res = games.stream()
                    .map(Game::computePower)
                    .reduce(Integer::sum)
                    .orElseThrow();
        }

        return print(res);
    }
}
