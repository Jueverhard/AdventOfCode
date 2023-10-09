package calendar.year._2015.day06;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class ProbablyAFireHazardTest extends BaseTest {
    private final ProbablyAFireHazard exercise = initializeExercise(ProbablyAFireHazard.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 377891),
                Arguments.of(Part.PART_1, true, 1000000),
                Arguments.of(Part.PART_2, false, 14110788),
                Arguments.of(Part.PART_2, true, 1000000)
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
