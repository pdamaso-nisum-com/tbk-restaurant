package cl.transbank.restaurante;

import cl.transbank.restaurante.domain.SalesIngress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestauranteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteApplication.class, args);
    }

    @Bean
    public List<SalesIngress> salesRegistry() {
        return new ArrayList<>();
    }
}
