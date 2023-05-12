package calendar.year._2022.day09;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class RopeBridgeTest extends BaseTest {

    private final RopeBridge exercise = initializeExercise(RopeBridge.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 6023),
                Arguments.of(Part.PART_1, true, 13),
                Arguments.of(Part.PART_2, false, 2533),
                Arguments.of(Part.PART_2, true, 1)
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
