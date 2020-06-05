package viktor.home.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import viktor.home.domain.User;
import viktor.home.repository.UserRepository;
import viktor.home.util.JwtUtil;

class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    JwtUtil jwtUtil = mock(JwtUtil.class);

    UserService sut = new UserService(userRepository,jwtUtil);

    @Test
    void create_user_should_create_user() {
        User user = User.builder().userName("username").password("pass").build();
        when(userRepository.save(user)).thenReturn(user);

        assertThat(sut.createUser(user)).isEqualTo(user);
        verify(userRepository).save(user);
    }
}