package calendar.year._2021.day06;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class LanternfishTest extends BaseTest {
    private final Lanternfish exercise = initializeExercise(Lanternfish.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 353274),
                Arguments.of(Part.PART_1, true, 5934),
//                Arguments.of(Part.PART_2, false, 19349),
                Arguments.of(Part.PART_2, true, 26984457539L)
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
