package cl.transbank.restaurant.controller;

import cl.transbank.restaurant.domain.SaleIngress;
import cl.transbank.restaurant.service.SalesBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SalesControllerTest {

    @Mock
    private SalesBook salesBook;
    @InjectMocks
    private SalesController salesController;

    @BeforeEach
    void setUp() {
        salesController = new SalesController(salesBook);
    }

    @Test
    void shouldRegisterSalesIngress() {

        SaleIngress saleIngress = getMockSalesIngress(LocalDate.now());

        given(salesBook.addEntry(saleIngress)).willReturn(saleIngress);

        ResponseEntity<SaleIngress> salesIngressRegistered = salesController.register(saleIngress);

        assertThat(salesIngressRegistered.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(salesIngressRegistered.getBody()).isEqualTo(saleIngress);
    }

    @Test
    void shouldGetAllSalesRegisteredOnSpecificDate() {

        LocalDate today = LocalDate.now();
        SaleIngress yesterdaySale = getMockSalesIngress(today.minusDays(1));
        salesController.register(yesterdaySale);
        SaleIngress todaySale = getMockSalesIngress(today);
        salesController.register(todaySale);

        given(salesBook.getEntriesBy(today)).willReturn(Collections.singletonList(todaySale));

        ResponseEntity<Collection<SaleIngress>> allTodaySales
                = salesController.getAll(today.getYear(), today.getMonth().getValue(), today.getDayOfMonth());

        assertThat(allTodaySales.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allTodaySales.getBody())
                .hasSize(1)
                .containsExactly(todaySale);
    }

    @Test
    void shouldGetEmptyListWhenNoSalesRegisteredOnSpecificDate() {

        LocalDate today = LocalDate.now();
        SaleIngress yesterdaySale = getMockSalesIngress(today.minusDays(1));
        salesController.register(yesterdaySale);
        SaleIngress todaySale = getMockSalesIngress(today);
        salesController.register(todaySale);

        LocalDate aWeekAgo = today.minusWeeks(1);
        ResponseEntity<Collection<SaleIngress>> allTodaySales
                = salesController.getAll(aWeekAgo.getYear(), aWeekAgo.getMonth().getValue(), aWeekAgo.getDayOfMonth());

        assertThat(allTodaySales.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allTodaySales.getBody()).isEmpty();
    }

    private SaleIngress getMockSalesIngress(LocalDate date) {
        return SaleIngress.builder()
                .commerce(123L)
                .date(date)
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();
    }
}