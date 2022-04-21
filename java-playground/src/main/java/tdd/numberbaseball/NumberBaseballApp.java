package tdd.numberbaseball;

import tdd.numberbaseball.core.BaseballGame;
import tdd.numberbaseball.core.NumberGenerator;
import tdd.numberbaseball.utils.GameUtils;

import java.io.IOException;

public class NumberBaseballApp {

    public static void main(String[] args) throws IOException {
        printMainLogo();
        String input = GameUtils.readInput();

        if (input.equals("s") || input.equals("S")) {
            startGame();
        }
    }


    private static void startGame() throws IOException {
        BaseballGame game = new BaseballGame();
        NumberGenerator numberGenerator = new NumberGenerator();
        game.initialize(numberGenerator.generate());
        game.run();
    }

    private static void printMainLogo() {
        System.out.println("=======================");
        System.out.println("숫자 야구 게임");
        System.out.println("시작 : s, 종료: any");
        System.out.println("=======================");
    }
}