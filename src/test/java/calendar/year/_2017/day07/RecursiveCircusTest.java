package calendar.year._2017.day07;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class RecursiveCircusTest extends BaseTest {
    private final RecursiveCircus exercise = initializeExercise(RecursiveCircus.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, "ahnofa"),
                Arguments.of(Part.PART_1, true, "tknk"),
                Arguments.of(Part.PART_2, false, 802),
                Arguments.of(Part.PART_2, true, 60)
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
