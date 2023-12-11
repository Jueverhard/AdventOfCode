package calendar.year._2023.day11;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class CosmicExpansionTest extends BaseTest {

    private final CosmicExpansion exercise = initializeExercise(CosmicExpansion.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 9522407),
                Arguments.of(Part.PART_1, true, 374),
                Arguments.of(Part.PART_2, false, 544723432977L),
                Arguments.of(Part.PART_2, true, 82000210)
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
