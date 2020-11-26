package cl.transbank.restaurante.messaging;

import cl.transbank.restaurante.domain.SalesIngress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesIngressListenerTest {

    @Autowired
    private SalesIngressListener salesIngressListener;

    @Test
    void shouldGetAllByDate() {
        LocalDate date = LocalDate.now();

        Collection<SalesIngress> allByDate = salesIngressListener.getAllByDate(date);

        assertThat(allByDate).isEmpty();
    }
}