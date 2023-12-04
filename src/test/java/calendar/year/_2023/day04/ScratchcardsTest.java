package calendar.year._2023.day04;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class ScratchcardsTest extends BaseTest {

    private final Scratchcards exercise = initializeExercise(Scratchcards.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 33950),
                Arguments.of(Part.PART_1, true, 13),
//                Arguments.of(Part.PART_2, false, 78826761),
                Arguments.of(Part.PART_2, true, 467835)
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
