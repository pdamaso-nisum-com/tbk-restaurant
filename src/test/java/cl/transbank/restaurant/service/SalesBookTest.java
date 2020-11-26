package cl.transbank.restaurant.service;

import cl.transbank.restaurant.domain.SalesIngress;
import cl.transbank.restaurant.messaging.SalesIngressListener;
import cl.transbank.restaurant.messaging.SalesIngressPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SalesBookTest {

    @Mock
    private SalesIngressPublisher salesIngressPublisher;
    @Mock
    private SalesIngressListener salesIngressListener;

    @InjectMocks
    private SalesBook salesBook;

    @Test
    void shouldAddEntry() {

        SalesIngress salesIngress = getSalesIngress(LocalDate.now());

        SalesIngress entry = salesBook.addEntry(salesIngress);

        assertThat(entry).isEqualTo(salesIngress);
        then(salesIngressPublisher).should().publish(salesIngress);
    }

    @Test
    void shouldGetEntriesByDate() {

        LocalDate today = LocalDate.now();

        salesBook.getEntriesBy(today);

        then(salesIngressListener).should().getAllByDate(today);
    }

    private SalesIngress getSalesIngress(LocalDate date) {
        return SalesIngress.builder()
                .commerce(123L)
                .date(date)
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();
    }
}