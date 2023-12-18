package calendar.year._2023.day14;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@EqualsAndHashCode
public class Dish {

    private final List<Position> rocks;

    private final List<Position> stops;

    private final int height;

    private final int width;

    public void rollNorth() {
        rocks.sort(Comparator.comparingInt(Position::getY));
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

    public void rollSouth() {
        rocks.sort(Comparator.comparingInt(Position::getY).reversed());
        for (Position rock : rocks) {
            int newY = Stream.of(rocks, stops)
                    .flatMap(List::stream)
                    .filter(position -> position.getX() == rock.getX() && position.getY() > rock.getY())
                    .min(Comparator.comparingInt(Position::getY))
                    .map(Position::getY)
                    .orElse(height) - 1;

            rock.setY(newY);
        }
    }

    public void rollWest() {
        rocks.sort(Comparator.comparingInt(Position::getX));
        for (Position rock : rocks) {
            int newX = Stream.of(rocks, stops)
                    .flatMap(List::stream)
                    .filter(position -> position.getY() == rock.getY() && position.getX() < rock.getX())
                    .max(Comparator.comparingInt(Position::getX))
                    .map(Position::getX)
                    .map(x -> x + 1)
                    .orElse(0);

            rock.setX(newX);
        }
    }

    public void rollEast() {
        rocks.sort(Comparator.comparingInt(Position::getX).reversed());
        for (Position rock : rocks) {
            int newX = Stream.of(rocks, stops)
                    .flatMap(List::stream)
                    .filter(position -> position.getY() == rock.getY() && position.getX() > rock.getX())
                    .min(Comparator.comparingInt(Position::getX))
                    .map(Position::getX)
                    .orElse(width) - 1;

            rock.setX(newX);
        }
    }

    public int computeValue() {
        return rocks.stream()
                .map(position -> height - position.getY())
                .reduce(0, Integer::sum);
    }
}
