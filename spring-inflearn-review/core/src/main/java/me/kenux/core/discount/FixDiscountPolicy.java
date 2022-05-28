package me.kenux.core.discount;

import me.kenux.core.member.Grade;
import me.kenux.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade().equals(Grade.VIP)) {
            return discountFixAmount;
        }
        return 0;
    }
}
