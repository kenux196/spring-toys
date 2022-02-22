package hello.hellospring.config;

import hello.hellospring.aop.TimeTraceApp;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //    private final DataSource dataSource;
//    private final EntityManager em;
    private final MemberRepository memberRepository;

//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }


    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceApp timeTraceApp() {
//        return new TimeTraceApp();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcTemplateRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
