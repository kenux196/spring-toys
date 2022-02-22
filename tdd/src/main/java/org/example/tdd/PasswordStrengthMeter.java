package org.example.tdd;

import org.example.tdd.enums.PasswordStrength;

import static org.example.tdd.enums.PasswordStrength.*;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if  (s == null || s.isEmpty()) return INVALID;

        int meetCounts = getMetCriteriaCounts(s);
        if (meetCounts <= 1) return WEAK;
        if (meetCounts == 2) return NORMAL;

        return STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int meetCounts = 0;
        if (s.length() >= 8) meetCounts++;
        if (isContainsNumber(s)) meetCounts++;
        if (isContainsUppercase(s)) meetCounts++;
        return meetCounts;
    }

    private boolean isContainsNumber(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean isContainsUppercase(String s) {
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                return true;
            }
        }
        return false;
    }
}