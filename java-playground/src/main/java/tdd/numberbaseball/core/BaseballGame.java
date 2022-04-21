package tdd.numberbaseball.core;

import tdd.numberbaseball.exception.GuessNumberException;
import tdd.numberbaseball.utils.GameUtils;

import java.io.IOException;

public class BaseballGame {

    private String answer;
    private int retryCount = 0;
    private GameStatus gameStatus = GameStatus.STOP;

    public void initialize(String answer) {
        gameStatus = GameStatus.READY;
        this.answer = answer;
    }

    public void run() throws IOException {
        System.out.println(GameMessage.START);
        while (true) {
            System.out.println(GameMessage.REQUEST_INPUT);
            final String input = GameUtils.readInput();
            if (input.equals("q")) {
                System.out.println(GameMessage.EXIT);
                return;
            }
            final String result = guessAndJudgeNumber(input);
            if (result.equals(GameMessage.WRONG_INPUT)) {
                System.out.println(result);
            } else {
                System.out.println(retryCount + " 번 시도 결과 = " + result);
            }

            if (result.equals(GameMessage.SUCCESS)) {
                return;
            }
        }
    }

    public String guessAndJudgeNumber(String guessNumber) {
        String result = "";
        try {
            validateGuessNumber(guessNumber);

            retryCount++;

            if (isOutCase(guessNumber)) {
                return GameMessage.SUCCESS;
            }

            result = discrimination(guessNumber);

            if (retryCount == 10) {
                return GameMessage.GAME_OVER;
            }
        } catch (GuessNumberException e) {
            result = GameMessage.WRONG_INPUT;
        }
        return result;
    }

    private void validateGuessNumber(String guessNumber) {
        if (guessNumber.length() != 3 || !GameUtils.isNumber(guessNumber)) {
            throw new GuessNumberException(GameMessage.WRONG_INPUT);
        }
    }

    private boolean isOutCase(String guessNumber) {
        return guessNumber.equals(answer);
    }

    private String discrimination(String number) {
        char[] answerCharArray = answer.toCharArray();
        char[] guess = number.toCharArray();
        int countStrike = 0;
        int countBall = 0;

        for (int i = 0; i < 3; i++) {
            if (answerCharArray[i] == guess[i]) {
                countStrike++;
            } else if (answer.contains(String.valueOf(guess[i]))) {
                countBall++;
            }
        }

        return countStrike + "s " + countBall + "b" ;
    }

    public boolean isReady() {
        return gameStatus == GameStatus.READY;
    }
}
