package me.kenux.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);


        // 잘못된 로그 사용법 -> 아래는 String 연산이 들어가게 된다.
        // log level 이 warn 이상으로 info 레벨이 적용되지 않더라도 아래는 로그는 연산이 수행된다.
        // 즉, 불필요한 연산을 하게 되므로 이런 형태의 로그 출력은 자제하도록 하고 위 예제와 같이 한다.
        log.info("info log = " + name);

        return "ok";
    }
}
