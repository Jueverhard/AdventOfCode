package calendar.year._2021.day01;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class SonarSweepTest extends BaseTest {
    private final SonarSweep exercise = initializeExercise(SonarSweep.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 1709),
                Arguments.of(Part.PART_1, true, 7),
                Arguments.of(Part.PART_2, false, 1761),
                Arguments.of(Part.PART_2, true, 5)
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
