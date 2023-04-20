package calendar.year._2017.day03;

import java.util.HashMap;
import java.util.Map;

public class Spiral {

    private final Map<Position, Integer> indexes;

    /**
     * Construit une spirale allant jusqu'à l'index demandé
     *
     * @param limit Limite supérieure des index de la spirale
     * @implNote Exemple de spirale, de limite 23
     * <p>17  16  15  14  13</p>
     * <p>18   5   4   3  12</p>
     * <p>19   6   1   2  11</p>
     * <p>20   7   8   9  10</p>
     * <p>21  22  23 -> ....</p>
     */
    public Spiral(int limit) {
        // Initialise le début de la spirale
        this.indexes = new HashMap<>();
        Position position = new Position(0, 0);
        int index = 1;
        this.indexes.put(position.copyOf(), index);

        // Indique la direction à suivre et le nombre de déplacements à faire avant de changer de direction
        Direction direction = Direction.RIGHT;
        int nbDeplacements = 1;

        // Tant que la limite n'a pas été atteinte, on enrichit la spirale
        while (index <= limit) {

            // Tous les deux changements de direction, le "rayon" de la spirale augmente
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < nbDeplacements; j++) {
                    position.move(direction);
                    this.indexes.put(position.copyOf(), ++index);
                }
                direction = direction.getNextDirection();
            }
            nbDeplacements++;
        }
    }

    public int computeDistanceFromCenter(int input) {
        Position targetPosition = this.indexes.entrySet().stream()
                .filter(entry -> input == entry.getValue())
                .findAny().orElseThrow(() -> new IllegalArgumentException("La spirale ne contient pas d'élément d'index " + input))
                .getKey();

        return Math.abs(targetPosition.getX()) + Math.abs(targetPosition.getY());
    }
}
