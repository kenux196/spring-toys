package me.kenux.core;

import me.kenux.core.discount.DiscountPolicy;
import me.kenux.core.discount.FixDiscountPolicy;
import me.kenux.core.discount.RateDiscountPolicy;
import me.kenux.core.member.MemberRepository;
import me.kenux.core.member.MemberService;
import me.kenux.core.member.MemberServiceImpl;
import me.kenux.core.member.MemoryMemberRepository;
import me.kenux.core.order.OrderService;
import me.kenux.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
