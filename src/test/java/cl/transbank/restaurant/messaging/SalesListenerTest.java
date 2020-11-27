package cl.transbank.restaurant.messaging;

import cl.transbank.restaurant.domain.input.SaleIngress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesListenerTest {

    @Autowired
    private SalesListener salesListener;

    @Test
    void shouldGetAllByDate() {
        LocalDate date = LocalDate.now();

        Collection<SaleIngress> allByDate = salesListener.getAllByDate(date);

        assertThat(allByDate).isEmpty();
    }
}