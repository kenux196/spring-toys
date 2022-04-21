package tdd.numberbaseball.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameUtils {

    private GameUtils() {
    }

    public static boolean isNumber(String str) {
        boolean result = true;
        if (str == null || str.length() == 0) {
            result = false;
        }
        else {
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i);
                if (c < 48 || c > 57) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static String readInput() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
}