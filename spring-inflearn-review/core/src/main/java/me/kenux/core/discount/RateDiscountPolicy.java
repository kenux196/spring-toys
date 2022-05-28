package me.kenux.core.discount;

import me.kenux.core.member.Grade;
import me.kenux.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10; // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade().equals(Grade.VIP)) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
