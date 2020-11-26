package cl.transbank.restaurant.mapper;

import cl.transbank.restaurant.domain.model.SaleEntity;
import cl.transbank.restaurant.domain.SaleIngress;
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

        SaleIngress saleIngress = SaleIngress.builder()
                .commerce(123L)
                .date(LocalDate.now())
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();

        SaleEntity sales = salesMapper.map(saleIngress);

        assertThat(sales.getId()).isNull();
        assertThat(sales.getCommerce()).isEqualTo(saleIngress.getCommerce());
        assertThat(sales.getDate()).isEqualTo(saleIngress.getDate());
        assertThat(sales.getTerminal()).isEqualTo(saleIngress.getTerminal());
        assertThat(sales.getAmount()).isEqualTo(saleIngress.getAmount());
    }

    @Test
    void shouldMapSalesToSalesIngress() {

        SaleEntity sales = SaleEntity.builder()
                .id(999L)
                .commerce(123L)
                .date(LocalDate.now())
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();

        SaleIngress saleIngress = salesMapper.map(sales);

        assertThat(saleIngress.getCommerce()).isEqualTo(sales.getCommerce());
        assertThat(saleIngress.getDate()).isEqualTo(sales.getDate());
        assertThat(saleIngress.getTerminal()).isEqualTo(sales.getTerminal());
        assertThat(saleIngress.getAmount()).isEqualTo(sales.getAmount());
    }
}