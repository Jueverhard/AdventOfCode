package calendar.year._2018.day06;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class ChronalCoordinatesTest extends BaseTest {
    private final ChronalCoordinates exercise = initializeExercise(ChronalCoordinates.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 4171),
                Arguments.of(Part.PART_1, true, 17),
                Arguments.of(Part.PART_2, false, 39545),
                Arguments.of(Part.PART_2, true, 16)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode, Object expectedResult) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        assertEquals(expectedResult, res);
    }
}
