package cl.transbank.restaurante.controller;

import cl.transbank.restaurante.domain.AuthenticationRequest;
import cl.transbank.restaurante.domain.AuthenticationResponse;
import cl.transbank.restaurante.security.AuthenticationService;
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