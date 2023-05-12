package calendar.year._2022.day12;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class HillClimbingAlgorithmTest extends BaseTest {

    private final HillClimbingAlgorithm exercise = initializeExercise(HillClimbingAlgorithm.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 339),
                Arguments.of(Part.PART_1, true, 25),
                Arguments.of(Part.PART_2, false, 332),
                Arguments.of(Part.PART_2, true, 23)
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
