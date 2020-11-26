package cl.transbank.restaurant.security;

public interface AuthenticationService {
    String authenticate(String username, String password);
}
