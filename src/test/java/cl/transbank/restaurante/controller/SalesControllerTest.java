package cl.transbank.restaurante.controller;

import cl.transbank.restaurante.domain.SalesIngress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SalesControllerTest {

    private SalesController salesController;
    private List<SalesIngress> salesRegistry;

    @BeforeEach
    void setUp() {
        salesRegistry = new ArrayList<>();
        salesController = new SalesController(salesRegistry);
    }

    @Test
    void shouldRegisterSalesIngress() {

        SalesIngress salesIngress = getMockSalesIngress(LocalDate.now());

        ResponseEntity<SalesIngress> salesIngressRegistered = salesController.register(salesIngress);

        assertThat(salesIngressRegistered.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(salesIngressRegistered.getBody()).isEqualTo(salesIngress);
        assertThat(salesRegistry)
                .hasSize(1)
                .contains(salesIngress);
    }

    @Test
    void shouldGetAllSalesRegisteredOnSpecificDate() {

        LocalDate today = LocalDate.now();
        SalesIngress yesterdaySale = getMockSalesIngress(today.minusDays(1));
        salesController.register(yesterdaySale);
        SalesIngress todaySale = getMockSalesIngress(today);
        salesController.register(todaySale);

        ResponseEntity<List<SalesIngress>> allTodaySales
                = salesController.getAll(today.getYear(), today.getMonth().getValue(), today.getDayOfMonth());

        assertThat(allTodaySales.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allTodaySales.getBody())
                .hasSize(1)
                .containsExactly(todaySale);
    }

    @Test
    void shouldGetEmptyListWhenNoSalesRegisteredOnSpecificDate() {

        LocalDate today = LocalDate.now();
        SalesIngress yesterdaySale = getMockSalesIngress(today.minusDays(1));
        salesController.register(yesterdaySale);
        SalesIngress todaySale = getMockSalesIngress(today);
        salesController.register(todaySale);

        LocalDate aWeekAgo = today.minusWeeks(1);
        ResponseEntity<List<SalesIngress>> allTodaySales
                = salesController.getAll(aWeekAgo.getYear(), aWeekAgo.getMonth().getValue(), aWeekAgo.getDayOfMonth());

        assertThat(allTodaySales.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allTodaySales.getBody()).isEmpty();
    }

    private SalesIngress getMockSalesIngress(LocalDate date) {
        return SalesIngress.builder()
                .commerce(123L)
                .date(date)
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();
    }
}