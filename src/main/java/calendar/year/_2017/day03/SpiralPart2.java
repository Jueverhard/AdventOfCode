package calendar.year._2017.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpiralPart2 {

    private final Map<Position, Integer> indexes;

    public SpiralPart2(int limit) {
        // Initialise le début de la spirale
        this.indexes = new HashMap<>();
        Position position = new Position(0, 0);
        int index = 1;
        this.indexes.put(position.copyOf(), index);

        // Indique la direction à suivre et le nombre de déplacements à faire avant de changer de direction
        Direction direction = Direction.RIGHT;
        int nbDeplacements = 1;

        // Tant que la limite n'a pas été atteinte, on enrichit la spirale
        while (index < limit) {

            // Tous les deux changements de direction, le "rayon" de la spirale augmente
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < nbDeplacements; j++) {
                    position.move(direction);
                    index = computeNewIndex(position);
                    this.indexes.put(position.copyOf(), index);
                    if (index >= limit) {
                        return;
                    }
                }
                direction = direction.getNextDirection();
            }
            nbDeplacements++;
        }
    }

    private int computeNewIndex(Position position) {
        // Calcul des positions adjacentes
        List<Position> adjacentPositions = IntStream.range(position.getX() - 1, position.getX() + 2)
                .mapToObj(x -> IntStream.range(position.getY() - 1, position.getY() + 2)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toList())
                ).flatMap(List::stream)
                .collect(Collectors.toList());

        // Somme des index des positions adjacentes
        return this.indexes.entrySet().stream()
                .filter(entry -> adjacentPositions.contains(entry.getKey()))
                .mapToInt(Entry::getValue)
                .sum();
    }

    public int getFirstValueGreaterThan(int input) {
        return this.indexes.values().stream()
                .filter(index -> index > input)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Il n'y a pas de valeur supérieure à " + input + " dans la spirale"));
    }
}
