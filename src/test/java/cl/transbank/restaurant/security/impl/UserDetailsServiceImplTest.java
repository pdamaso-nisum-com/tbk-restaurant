package cl.transbank.restaurant.security.impl;

import cl.transbank.restaurant.domain.model.UserEntity;
import cl.transbank.restaurant.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(UserEntity.builder().username("username").password("some-encoded-password").build());
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

    @Test
    void shouldFailWhenUsernameNotFound() {

        String notExistingUsername = "other-username";
        UsernameNotFoundException exception = Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(notExistingUsername));

        assertThat(exception.getMessage()).isEqualTo(notExistingUsername);
    }
}