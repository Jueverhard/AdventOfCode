package calendar.year._2022.day06;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class TuningTroubleTest extends BaseTest {

    private final TuningTrouble exercise = initializeExercise(TuningTrouble.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 1140),
                Arguments.of(Part.PART_1, true, 7),
                Arguments.of(Part.PART_2, false, 3495),
                Arguments.of(Part.PART_2, true, 19)
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
