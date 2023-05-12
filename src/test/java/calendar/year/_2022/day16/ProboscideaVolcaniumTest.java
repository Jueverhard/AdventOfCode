package calendar.year._2022.day16;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class ProboscideaVolcaniumTest extends BaseTest {

    private final ProboscideaVolcanium exercise = initializeExercise(ProboscideaVolcanium.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 1850),
                Arguments.of(Part.PART_1, true, 1651),
                // TODO JEV : second part hasn't been solved yet
                // Arguments.of(Part.PART_2, false),
                 Arguments.of(Part.PART_2, true, 1707)
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
