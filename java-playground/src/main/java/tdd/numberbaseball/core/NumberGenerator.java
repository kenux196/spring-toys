package tdd.numberbaseball.core;

import java.util.Random;

public class NumberGenerator {
    private final Random random = new Random();

    public String generate() {
        String randomNumber = "000";
        while (!isCorrectNumber(randomNumber)) {
            randomNumber = String.valueOf(random.nextInt(999));
        }
        return randomNumber;
    }

    public boolean isCorrectNumber(String number) {
        return !number.contains("0") && !hasSameNumber(number);
    }

    private boolean hasSameNumber(String num) {
        char[] numbers = String.valueOf(num).toCharArray();
        return numbers[0] == numbers[1] ||
                numbers[0] == numbers[2] ||
                numbers[1] == numbers[2];
    }
}
