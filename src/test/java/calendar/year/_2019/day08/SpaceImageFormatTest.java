package calendar.year._2019.day08;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class SpaceImageFormatTest extends BaseTest {
    private final SpaceImageFormat exercise = initializeExercise(SpaceImageFormat.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 1935),
//                First and second part do not share the same test input
//                Arguments.of(Part.PART_1, true, 1),
                Arguments.of(Part.PART_2, false, "011001111010000100101000010010100001000010010100001000011100100001001010000100001000010000100101000010010100001000010010100000110010000111100110011110"),
                Arguments.of(Part.PART_2, true, "0110")
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
