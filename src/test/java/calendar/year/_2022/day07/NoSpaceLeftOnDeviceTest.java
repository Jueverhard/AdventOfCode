package calendar.year._2022.day07;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class NoSpaceLeftOnDeviceTest extends BaseTest {

    private final NoSpaceLeftOnDevice exercise = initializeExercise(NoSpaceLeftOnDevice.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 1667443),
                Arguments.of(Part.PART_1, true, 95437),
                Arguments.of(Part.PART_2, false, 8998590),
                Arguments.of(Part.PART_2, true, 24933642)
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
