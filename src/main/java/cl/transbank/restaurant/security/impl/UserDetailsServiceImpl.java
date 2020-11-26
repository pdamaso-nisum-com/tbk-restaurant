package cl.transbank.restaurant.security.impl;

import cl.transbank.restaurant.domain.model.UserEntity;
import cl.transbank.restaurant.repository.UsersRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = usersRepository.findByUsernameEquals(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        UserEntity mockUser = UserEntity.builder()
                .username("username")
                .password(passwordEncoder.encode("password"))
                .build();
        usersRepository.save(mockUser);
    }
}
