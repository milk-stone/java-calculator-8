package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationCustomTest extends NsTest {
    @ParameterizedTest
    @MethodSource("provideTestCasesWithoutCustomDelimiter")
    void 정상_기본_테스트(String input, int expectedSum) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).contains("결과 : " + expectedSum);
        });
    }

    private static Stream<Arguments> provideTestCasesWithoutCustomDelimiter() {
        return Stream.of(
                Arguments.of("\n", 0),
                Arguments.of("3", 3),
                Arguments.of("1,2", 3),
                Arguments.of("1:2", 3),
                Arguments.of("1:2,3", 6)
        );
    }


    @ParameterizedTest
    @MethodSource("provideTestCasesWithCustomDelimiter")
    void 정상_커스텀_테스트(String input, int expectedSum) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).contains("결과 : " + expectedSum);
        });
    }

    private static Stream<Arguments> provideTestCasesWithCustomDelimiter() {
        return Stream.of(
                Arguments.of("//;\\n", 0),
                Arguments.of("//;\\n1", 1),
                Arguments.of("//;\\n1,2", 3),
                Arguments.of("//;\\n1:2", 3),
                Arguments.of("//;\\n1;2", 3),
                Arguments.of("//;\\n1:2;3", 6),
                Arguments.of("//[\\n1[2[3", 6),
                Arguments.of("//(\\n1(2:3(4", 10),
                Arguments.of("//~\\n1~2", 3),
                Arguments.of("//!\\n1!2", 3),
                Arguments.of("//@\\n1@2", 3),
                Arguments.of("//#\\n1#2", 3),
                Arguments.of("//$\\n1$2", 3),
                Arguments.of("//%\\n1%2", 3),
                Arguments.of("//^\\n1^2", 3),
                Arguments.of("//&\\n1&2", 3),
                Arguments.of("//*\\n1*2", 3),
                Arguments.of("//)\\n1)2", 3),
                Arguments.of("//_\\n1_2", 3),
                Arguments.of("//-\\n1-2", 3),
                Arguments.of("//+\\n1+2", 3),
                Arguments.of("//=\\n1=2", 3),
                Arguments.of("//{\\n1{2", 3),
                Arguments.of("//}\\n1}2", 3),
                Arguments.of("//]\\n1]2", 3),
                Arguments.of("//|\\n1|2", 3),
                Arguments.of("//\\\\n1\\2", 3),
                Arguments.of("//\"\\n1\"2", 3),
                Arguments.of("//\'\\n1\'2", 3),
                Arguments.of("//<\\n1<2", 3),
                Arguments.of("//>\\n1>2", 3),
                Arguments.of("//?\\n1?2", 3),
                Arguments.of("///\\n1/2", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInputs")
    void 예외처리_테스트(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    private static Stream<Arguments> provideInvalidInputs() {
        return Stream.of(
                Arguments.of("-1,2,3"),
                Arguments.of("//;\\n1;-2"),
                Arguments.of("//A\\n1A2"),
                Arguments.of("1,a,2"),
                Arguments.of("//(\\n1(2:3)4"),
                Arguments.of("//3\\n132")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
