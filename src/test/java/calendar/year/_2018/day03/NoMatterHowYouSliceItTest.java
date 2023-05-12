package calendar.year._2018.day03;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class NoMatterHowYouSliceItTest extends BaseTest {
    private final NoMatterHowYouSliceIt exercise = initializeExercise(NoMatterHowYouSliceIt.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 118840),
                Arguments.of(Part.PART_1, true, 4),
                Arguments.of(Part.PART_2, false, 919),
                Arguments.of(Part.PART_2, true, 3)
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
