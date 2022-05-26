package me.kenux.playground.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ExceptionTest {


    private final App app = new App();

    @Test
    @DisplayName("Checked 예외 발생 -> 잡아서 Runtime 예외로 변경해서 던진다.")
    void checkedExceptionTest() {
        assertThatThrownBy(app::checkedExceptionThrowRuntimeException)
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Checked 예외 발생 -> 잡아서 직접 처리한다.")
    void checkedExceptionCatchTest() {
        app.catchCheckedException();
    }

    @Test
    @DisplayName("UnChecked 예외 발생 -> 밖으로 그냥 던진다.")
    void runtimeExceptionTest() {
        assertThatThrownBy(app::runtimeException)
                .isInstanceOf(RuntimeException.class);
    }


    static class App {

        public void checkedExceptionThrowRuntimeException() {
            log.info("비즈니스 로직 수행 중...");
            try {
                checkedException();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            log.info("비즈니스 로직 종료");
        }

        public void catchCheckedException() {
            log.info("비즈니스 로직 수행 중...");
            try {
                checkedException();
            } catch (SQLException e) {
                log.error("예외 메시지: ", e);
            }
            log.info("비즈니스 로직 종료");
        }

        public void runtimeException() {
            log.info("비즈니스 로직 수행 중...");
            unCheckedException();
            log.info("비즈니스 로직 종료");
        }


        public void checkedException() throws SQLException {
            throw new SQLException("예외 발생");
        }

        public void unCheckedException() {
            throw new RuntimeException("예외 발생");
        }

        public void customException() {
            throw new MyCustomException("사용자 정의 예외 발생");
        }
    }

    static class MyCustomException extends RuntimeException {

        public MyCustomException(String message) {
            super(message);
        }
    }

}

