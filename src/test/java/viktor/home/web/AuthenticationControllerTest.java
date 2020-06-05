package viktor.home.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import viktor.home.domain.AutenticationRequest;
import viktor.home.util.JwtUtil;

import java.util.Collection;

class AuthenticationControllerTest {

    private  AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private  UserDetailsService userDetailsService = mock(UserDetailsService.class);
    private  JwtUtil jwtUtil = mock(JwtUtil.class);

    AuthenticationController sut = new AuthenticationController(
            authenticationManager,
            userDetailsService,
            jwtUtil
    );

    @Test
    void should_create_authentication_token() throws Exception {

        AutenticationRequest authRequest = AutenticationRequest.builder().userName("Michael").password("Jordan").build();

        val result = sut.createAuthenticationToken(authRequest);

        System.out.println("asd");

    }
}