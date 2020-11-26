package cl.transbank.restaurante.service;

import cl.transbank.restaurante.domain.Sales;
import cl.transbank.restaurante.domain.SalesIngress;
import cl.transbank.restaurante.mapper.SalesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SalesMapperTest {

    private SalesMapper salesMapper;

    @BeforeEach
    void setUp() {
        salesMapper = new SalesMapper();
    }

    @Test
    void shouldMapSalesIngressToSales() {

        SalesIngress salesIngress = SalesIngress.builder()
                .commerce(123L)
                .date(LocalDate.now())
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();

        Sales sales = salesMapper.map(salesIngress);

        assertThat(sales.getId()).isNull();
        assertThat(sales.getCommerce()).isEqualTo(salesIngress.getCommerce());
        assertThat(sales.getDate()).isEqualTo(salesIngress.getDate());
        assertThat(sales.getTerminal()).isEqualTo(salesIngress.getTerminal());
        assertThat(sales.getAmount()).isEqualTo(salesIngress.getAmount());
    }

    @Test
    void shouldMapSalesToSalesIngress() {

        Sales sales = Sales.builder()
                .id(999L)
                .commerce(123L)
                .date(LocalDate.now())
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();

        SalesIngress salesIngress = salesMapper.map(sales);

        assertThat(salesIngress.getCommerce()).isEqualTo(sales.getCommerce());
        assertThat(salesIngress.getDate()).isEqualTo(sales.getDate());
        assertThat(salesIngress.getTerminal()).isEqualTo(sales.getTerminal());
        assertThat(salesIngress.getAmount()).isEqualTo(sales.getAmount());
    }
}