package calendar.year._2023.day14;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
public class Dish {

    private final List<Position> rocks;

    private final List<Position> stops;

    private final int height;

    public void rollNorth() {
        for (Position rock : rocks) {
            int newY = Stream.of(rocks, stops)
                    .flatMap(List::stream)
                    .filter(position -> position.getX() == rock.getX() && position.getY() < rock.getY())
                    .max(Comparator.comparingInt(Position::getY))
                    .map(Position::getY)
                    .map(y -> y + 1)
                    .orElse(0);

            rock.setY(newY);
        }
    }

    public int computeValue() {
        return rocks.stream()
                .map(position -> height - position.getY())
                .reduce(0, Integer::sum);
    }
}
