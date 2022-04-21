package tdd.numberbaseball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.numberbaseball.core.BaseballGame;
import tdd.numberbaseball.core.GameMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseballGameTest {

    BaseballGame baseBallGame;

    @BeforeEach
    void setup() {
        baseBallGame = new BaseballGame();
    }

    @Test
    void 게임이_시작되면_모든_상태를_초기화한다() {
        baseBallGame.initialize("123");

        assertThat(baseBallGame.isReady()).isTrue();
    }

    @Test
    @DisplayName("숫자 3개의 위치와 값이 모두 일치하면, out을 리턴한다.")
    void guessNumberTest_Out상태_체크() {
        // given
        baseBallGame.initialize("123");

        // when
        String result = baseBallGame.guessAndJudgeNumber("123");

        // then
        assertThat(result).isEqualTo(GameMessage.SUCCESS);
    }

    @Test
    @DisplayName("숫자의 값과 위치가 일치하는 것이 1개이면 1s 리턴, 2개이면 2s 리턴")
    void guessAndJudgementNumberTest_strike() {
        baseBallGame.initialize("123");

        String result = baseBallGame.guessAndJudgeNumber("145");
        assertThat(result).contains("1s");

        result = baseBallGame.guessAndJudgeNumber("125");
        assertThat(result).contains("2s");
    }


    @Test
    @DisplayName("추측 숫자가 정답 숫자에 포함되어 있으면 있는 만큼 b 리턴")
    void guessAndJudgementNumberTest_ball() {
        baseBallGame.initialize("123");

        String result = baseBallGame.guessAndJudgeNumber("312");
        assertThat(result).contains("3b");

        String result2 = baseBallGame.guessAndJudgeNumber("412");
        assertThat(result2).contains("2b");

        String result3 = baseBallGame.guessAndJudgeNumber("452");
        assertThat(result3).contains("1b");
    }

    @Test
    @DisplayName("추측 숫자와 정답 숫자가 아무것도 없다면 0s 0b 리턴")
    void guessAndJudgementNumberTest_0s_0b() {
        baseBallGame.initialize("123");

        String result = baseBallGame.guessAndJudgeNumber("456");
        assertThat(result).contains("0s 0b");
    }

    @Test
    @DisplayName("예측 시도는 10회까지 가능하고 넘어가면 게임 종료")
    void retryCount_10회_넘어가면_게임종료() {
        baseBallGame.initialize("123");
        String result = "";
        for (int i = 0; i < 10; i++) {
            result = baseBallGame.guessAndJudgeNumber("456");
        }

        assertThat(result).isEqualTo(GameMessage.GAME_OVER);
    }

    @Test
    @DisplayName("사용자 입력에 숫자 이외의 값이 있으면 에외처리 한다.")
    void exceptionCase_guessNumberIsNotNumeric() {
        String result = baseBallGame.guessAndJudgeNumber("12a");
        assertThat(result).isEqualTo(GameMessage.WRONG_INPUT);
//        assertThrows(GuessNumberException.class, () -> baseBallGame.guessAndJudgeNumber("2dk"));
    }

    @Test
    @DisplayName("사용자의 입력에 대한 검증을 한다. 3자리가 아닌 경우, 오류 표시")
    void validateUserInput() {
        String result = baseBallGame.guessAndJudgeNumber("2");
        assertThat(result).isEqualTo(GameMessage.WRONG_INPUT);
        result = baseBallGame.guessAndJudgeNumber("12");
        assertThat(result).isEqualTo(GameMessage.WRONG_INPUT);
        result = baseBallGame.guessAndJudgeNumber("1222");
        assertThat(result).isEqualTo(GameMessage.WRONG_INPUT);

//        assertThrows(GuessNumberException.class, () -> baseBallGame.guessAndJudgeNumber("2"));
//        assertThrows(GuessNumberException.class, () -> baseBallGame.guessAndJudgeNumber("12"));
//        assertThrows(GuessNumberException.class, () -> baseBallGame.guessAndJudgeNumber("1234"));
    }
}
