package cl.transbank.restaurant.security.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDetailsService = new UserDetailsServiceImpl(passwordEncoder);
    }

    @Test
    void shouldLoadUserByUsername() {

        String username = "username";
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(username);
        assertThat(userDetails.getPassword()).isNotNull();
        assertThat(userDetails.getAuthorities()).isEmpty();
    }
}