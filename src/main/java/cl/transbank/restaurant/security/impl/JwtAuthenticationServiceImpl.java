package cl.transbank.restaurant.security.impl;

import cl.transbank.restaurant.security.AuthenticationService;
import cl.transbank.restaurant.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtAuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                        TokenService tokenService,
                                        @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = tokenService.generateToken(userDetails);
        log.info("userAuthenticated, username={}", username);
        return token;
    }
}
