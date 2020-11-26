package cl.transbank.restaurant.security.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class AuthenticationEntryPointImplTest {

    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @BeforeEach
    void setUp() {
        authenticationEntryPoint = new AuthenticationEntryPointImpl();
    }

    @Test
    void shouldCommence() throws IOException {

        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException authException = new BadCredentialsException("some error");

        authenticationEntryPoint.commence(null, response, authException);

        then(response).should().sendError(401, authException.getMessage());
    }
}