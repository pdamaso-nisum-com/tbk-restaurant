package cl.transbank.restaurant.service;

import cl.transbank.restaurant.domain.SaleIngress;
import cl.transbank.restaurant.messaging.SalesListener;
import cl.transbank.restaurant.messaging.SalesPublisher;
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
    private SalesPublisher salesPublisher;
    @Mock
    private SalesListener salesListener;

    @InjectMocks
    private SalesBook salesBook;

    @Test
    void shouldAddEntry() {

        SaleIngress saleIngress = getSalesIngress(LocalDate.now());

        SaleIngress entry = salesBook.addEntry(saleIngress);

        assertThat(entry).isEqualTo(saleIngress);
        then(salesPublisher).should().publish(saleIngress);
    }

    @Test
    void shouldGetEntriesByDate() {

        LocalDate today = LocalDate.now();

        salesBook.getEntriesBy(today);

        then(salesListener).should().getAllByDate(today);
    }

    private SaleIngress getSalesIngress(LocalDate date) {
        return SaleIngress.builder()
                .commerce(123L)
                .date(date)
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();
    }
}