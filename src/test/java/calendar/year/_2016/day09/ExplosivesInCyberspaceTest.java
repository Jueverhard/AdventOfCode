package calendar.year._2016.day09;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class ExplosivesInCyberspaceTest extends BaseTest {

    private final ExplosivesInCyberspace exercise = initializeExercise(ExplosivesInCyberspace.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 183269),
                Arguments.of(Part.PART_1, true, 18)//,
//                Arguments.of(Part.PART_2, false, 0),
//                Arguments.of(Part.PART_2, true, 0)
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
