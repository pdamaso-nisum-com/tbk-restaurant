package cl.transbank.restaurant.controller;

import cl.transbank.restaurant.domain.login.AuthenticationRequest;
import cl.transbank.restaurant.domain.login.AuthenticationResponse;
import cl.transbank.restaurant.security.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login")
    @ApiOperation(value = "User login to get JWT token",
            response = AuthenticationResponse.class)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) {
        String token = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(authRequest.getUsername(), token));
    }
}
