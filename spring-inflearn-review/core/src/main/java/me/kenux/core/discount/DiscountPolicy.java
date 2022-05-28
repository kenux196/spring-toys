package me.kenux.core.discount;

import me.kenux.core.member.Member;

public interface DiscountPolicy {

    /**
     * 할인 대상 금액 계산
     * @param member 멤버
     * @param price 가격
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
