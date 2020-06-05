package viktor.home.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import viktor.home.domain.User;
import viktor.home.service.UserService;

class UserControllerTest {
    UserService userService = mock(UserService.class);
    UserController sut = new UserController(userService);

    @Test
    void create_user_should_call_service_and_return_correct_message() {
        User user = User.builder().id(1L).userName("username").password("pass").build();
        when(userService.createUser(user)).thenReturn(user);

        String result = sut.createUser(user);

        verify(userService).createUser(user);
        assertThat(result).isEqualTo("User username created successfully");
    }
}