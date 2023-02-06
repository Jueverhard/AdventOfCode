package calendar.year._2022.day17;

import calendar.BaseTest;
import calendar.year.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class PyroclasticFlowTest extends BaseTest {

    private final PyroclasticFlow exercise = initializeExercise(PyroclasticFlow.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false),
                Arguments.of(Part.PART_1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        long expectedResult;
        if (Part.PART_1.equals(part)) {
            expectedResult = testMode ? 3068 : 3217;
        } else {
            // TODO JEV : real result isn't known yet, as the second part hasn't been solved
            expectedResult = testMode ? 1514285714288L : 42;
        }
        assertEquals(expectedResult, res);
    }
}
