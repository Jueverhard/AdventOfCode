package calendar.year._2023.day13;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class PointOfIncidenceTest extends BaseTest {

    private final PointOfIncidence exercise = initializeExercise(PointOfIncidence.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 28895),
                Arguments.of(Part.PART_1, true, 405),
                Arguments.of(Part.PART_2, false, 31603),
                Arguments.of(Part.PART_2, true, 400)
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
