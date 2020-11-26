package cl.transbank.restaurant.security.impl;

import cl.transbank.restaurant.security.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;
    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationServiceImpl authenticationService;

    @Test
    void shouldAuthenticateByUsernameAndPassword() {

        String username = "user";
        String password = "pass";

        UserDetails userDetails = new User(username, password, Collections.emptyList());
        given(userDetailsService.loadUserByUsername(username))
                .willReturn(userDetails);

        String token = "some-generated-token";
        given(tokenService.generateToken(userDetails))
                .willReturn(token);

        String expectedToken = authenticationService.authenticate(username, password);

        assertThat(expectedToken)
                .isNotNull()
                .isEqualTo(token);
    }
}