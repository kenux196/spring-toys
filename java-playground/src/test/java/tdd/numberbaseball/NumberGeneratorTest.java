package tdd.numberbaseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.numberbaseball.core.NumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class NumberGeneratorTest {

    private NumberGenerator numberGenerator = new NumberGenerator();

    @Test
    @DisplayName("야구 게임은 1~9까지의 숫자 3개를 생성하게 된다.")
    void initGame() {
        String number = numberGenerator.generate();

        assertThat(number)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    @DisplayName("생성된 숫자가 모두 다른 숫자이면 true 리턴")
    void validateGeneratedNumber_모두_다른_숫자인_경우() {
        String answer = "123";
        boolean result = numberGenerator.isCorrectNumber(answer);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("생성된 숫자에 동일한 수가 포함된 경우 false 리턴")
    void validateGeneratedNumber_같은_숫자_포함된_경우() {
        String answer = "111";
        boolean result = numberGenerator.isCorrectNumber(answer);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("생성된 숫자에 0이 있는 경우, false 리턴")
    void validateGeneratedNumber_0_포함된_경우() {
        String answer = "101";
        boolean result = numberGenerator.isCorrectNumber(answer);
        assertThat(result).isFalse();
    }
}