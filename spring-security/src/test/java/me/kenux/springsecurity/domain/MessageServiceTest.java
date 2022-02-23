package me.kenux.springsecurity.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
//@WithMockUser
class MessageServiceTest {

    @Autowired
    MessageService messageService;

//    @Test
//    @WithMockUser
    void getMessageUnauthenticated() {
        assertThatThrownBy(() -> messageService.getMessage())
                .isInstanceOf(AuthenticationCredentialsNotFoundException.class);
    }

    @Test
    @WithMockUser
    void getMessage1() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);
        assertThat(message).contains("user");
    }

    @Test
    @WithMockUser("customUsername")
    void getMessageWithMockingCustomUserName() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);
        assertThat(message).contains("customUsername");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void getMessageWithMockingUserCustomUser() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);
        assertThat(message).contains("admin");

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN"})
    void getMessageWithMockingUserCustomUser2() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);
        assertThat(message).contains("admin");

    }

    @Test
    @WithAnonymousUser
    void anonymous() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);
        assertThat(message).contains("anonymous");
    }

    @Test
    @WithUserDetails()
    void getMessageWithUserDetails() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);

        assertThat(message).contains("user");
    }

    @Test
    @WithUserDetails("kenux")
    void getMessageWithUserDetailsCustomUsername() {
        final String message = messageService.getMessage();
        System.out.println("message = " + message);
        assertThat(message).contains("kenux");
    }

}