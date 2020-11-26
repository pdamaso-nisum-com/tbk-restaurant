package cl.transbank.restaurant.security.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenServiceImplTest {

    private JwtTokenServiceImpl tokenService;

    @BeforeEach
    void setUp() {
        String secret = "E(H+MbQeThWmZq4t7w!z%C*F)J@NcRfUjXn2r5u8x/A?D(G+KaPdSgVkYp3s6v9y";
        tokenService = new JwtTokenServiceImpl(secret);
    }

    @Test
    void shouldGenerateToken() {

        UserDetails userDetails = new User("user", "pass", Collections.emptyList());

        String token = tokenService.generateToken(userDetails);

        assertThat(token).isNotNull();
    }

    @Test
    void shouldValidateToken() {

        UserDetails userDetails = new User("user", "pass", Collections.emptyList());

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxOTA2MjUxOTkyfQ.CLW6ZQxDWEdwjMbRcbaMcYfU5iBnvZgR8fxHhDDemGPpCVnWLRqbIQrvKkn1lqJezKi1zK9YCsq6WYSt_wAv7w";

        boolean isTokenValid = tokenService.validateToken(token, userDetails);

        assertThat(isTokenValid).isTrue();
    }

    @Test
    void shouldGetUsernameFromToken() {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIn0.0wLjukJrnzQz--79qE_zFYWMfP8m1Mvs-bpzuqUP6a-LhXM5K6G_tBda4T8F2ZO5I0erTuAj3pLemHO14nRkpw";

        String usernameFromToken = tokenService.getUsernameFromToken(token);

        assertThat(usernameFromToken).isEqualTo("user");
    }
}