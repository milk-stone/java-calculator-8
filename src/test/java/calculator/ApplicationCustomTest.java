package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationCustomTest extends NsTest {
    @Test
    void 정상_기본_TC1() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 정상_기본_TC2() {
        assertSimpleTest(() -> {
            run("3");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 정상_기본_TC3() {
        assertSimpleTest(() -> {
            run("1,2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 정상_기본_TC4() {
        assertSimpleTest(() -> {
            run("1:2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 정상_기본_TC5() {
        assertSimpleTest(() -> {
            run("1:2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정상_커스텀_TC1() {
        assertSimpleTest(() -> {
            run("//;\\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 정상_커스텀_TC2() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 정상_커스텀_TC3() {
        assertSimpleTest(() -> {
            run("//;\\n1,2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 정상_커스텀_TC4() {
        assertSimpleTest(() -> {
            run("//;\\n1:2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 정상_커스텀_TC5() {
        assertSimpleTest(() -> {
            run("//;\\n1;2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 정상_커스텀_TC6() {
        assertSimpleTest(() -> {
            run("//;\\n1:2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
