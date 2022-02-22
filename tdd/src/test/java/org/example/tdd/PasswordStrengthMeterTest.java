package org.example.tdd;

import org.example.tdd.enums.PasswordStrength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.tdd.enums.PasswordStrength.*;

class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertThat(result).isEqualTo(expStr);
    }

    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야 함")
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", STRONG);
        assertStrength("abc1!Add", STRONG);
    }

    @Test
    @DisplayName("길이가 8자 미만이고, 나머지 조건은 충족하면 암호 강도는 보통이어야 함")
    void meetsOtherCriteria_except_for_length_Then_Normal() {
        assertStrength("ab12!@A", NORMAL);
        assertStrength("ab12!A", NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하면 암호 강도는 보통이어야 함")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", NORMAL);
    }

    @Test
    @DisplayName("값이 null 경우, INVALID 를 리턴해야 함")
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("값이 빈 문자열인 경우, INVALID 를 리턴한다")
    void emptyInput_Then_Invalid() {
        assertStrength("", INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고, 나머지 조건을 충족하는 경우 암호 강도는 보통이어야 함")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12@!df", NORMAL);
    }

    @Test
    @DisplayName("길이가 8자 이상인 조건만 충족하는 경우, 암호 강도는 약함이어야 함")
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abcdefgd", WEAK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하는 경우, 암호 강도는 약함이어야 함")
    void meetsOnlyNumberCriteria_Then_Weak() {
        assertStrength("12345", WEAK);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족하는 경우, 암호 강도는 약함이어야 함")
    void meetsOnlyUppercaseCriteria_Then_Weak() {
        assertStrength("ABCDE", WEAK);
        assertStrength("ABCDE!", WEAK);
    }

    @Test
    @DisplayName("아무조건도 충족하지 않는 경우, 암호 강도는 약함이다.")
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", WEAK);
    }
}