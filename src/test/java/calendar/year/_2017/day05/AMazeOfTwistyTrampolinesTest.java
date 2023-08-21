package calendar.year._2017.day05;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class AMazeOfTwistyTrampolinesTest extends BaseTest {
    private final AMazeOfTwistyTrampolines exercise = initializeExercise(AMazeOfTwistyTrampolines.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 374269),
                Arguments.of(Part.PART_1, true, 5),
                Arguments.of(Part.PART_2, false, 27720699),
                Arguments.of(Part.PART_2, true, 10)
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
