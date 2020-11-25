package cl.transbank.restaurante.security;

public interface AuthenticationService {
    String authenticate(String username, String password);
}
