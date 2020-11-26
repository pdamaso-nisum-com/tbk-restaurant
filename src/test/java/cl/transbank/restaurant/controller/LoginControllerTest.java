package cl.transbank.restaurant.controller;

import cl.transbank.restaurant.domain.login.AuthenticationRequest;
import cl.transbank.restaurant.domain.login.AuthenticationResponse;
import cl.transbank.restaurant.security.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void shouldLoginValidUser() {

        String username = "user";
        String password = "pass";
        AuthenticationRequest authRequest = new AuthenticationRequest(username, password);

        String token = "some-token-value";
        given(authenticationService.authenticate(username, password)).willReturn(token);

        ResponseEntity<AuthenticationResponse> responseEntity = loginController.login(authRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getUsername()).isEqualTo(username);
        assertThat(responseEntity.getBody().getToken()).isEqualTo(token);
    }
}