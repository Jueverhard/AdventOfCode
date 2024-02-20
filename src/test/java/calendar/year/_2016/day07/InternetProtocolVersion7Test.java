package calendar.year._2016.day07;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class InternetProtocolVersion7Test extends BaseTest {
    private final InternetProtocolVersion7 exercise = initializeExercise(InternetProtocolVersion7.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
//                Arguments.of(Part.PART_1, false, 105),
//                Arguments.of(Part.PART_1, true, 2),
                Arguments.of(Part.PART_2, false, 368), // FIXME JEV : wrong result
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
