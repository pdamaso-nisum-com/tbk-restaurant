package cl.transbank.restaurant.mapper;

import cl.transbank.restaurant.domain.input.SaleIngress;
import cl.transbank.restaurant.domain.input.SaleIngressItem;
import cl.transbank.restaurant.domain.model.SaleEntity;
import cl.transbank.restaurant.domain.model.SaleItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SalesMapperTest {

    private SalesMapper salesMapper;

    @BeforeEach
    void setUp() {
        salesMapper = new SalesMapper();
    }

    @Test
    void shouldMapSalesIngressToSales() {

        SaleIngressItem saleIngressItem = SaleIngressItem.builder()
                .product("some-product")
                .price(BigDecimal.valueOf(5.61))
                .quantity(2)
                .total(BigDecimal.valueOf(11.22))
                .build();
        List<SaleIngressItem> saleIngressItems = Collections.singletonList(saleIngressItem);
        SaleIngress saleIngress = SaleIngress.builder()
                .commerce(123L)
                .date(LocalDate.now())
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .items(saleIngressItems)
                .build();

        SaleEntity sales = salesMapper.map(saleIngress);

        assertThat(sales.getId()).isNull();
        assertThat(sales.getCommerce()).isEqualTo(saleIngress.getCommerce());
        assertThat(sales.getDate()).isEqualTo(saleIngress.getDate());
        assertThat(sales.getTerminal()).isEqualTo(saleIngress.getTerminal());
        assertThat(sales.getAmount()).isEqualTo(saleIngress.getAmount());
        assertThat(sales.getItems()).isNotEmpty().hasSize(1);
    }

    @Test
    void shouldMapSalesToSalesIngress() {

        SaleItemEntity saleItemEntity = SaleItemEntity.builder()
                .id(1L)
                .product("some-product")
                .price(BigDecimal.valueOf(5.61))
                .quantity(2)
                .total(BigDecimal.valueOf(11.22))
                .build();
        List<SaleItemEntity> saleEntityItems = Collections.singletonList(saleItemEntity);
        SaleEntity sales = SaleEntity.builder()
                .id(999L)
                .commerce(123L)
                .date(LocalDate.now())
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .items(saleEntityItems)
                .build();

        SaleIngress saleIngress = salesMapper.map(sales);

        assertThat(saleIngress.getCommerce()).isEqualTo(sales.getCommerce());
        assertThat(saleIngress.getDate()).isEqualTo(sales.getDate());
        assertThat(saleIngress.getTerminal()).isEqualTo(sales.getTerminal());
        assertThat(saleIngress.getAmount()).isEqualTo(sales.getAmount());
        assertThat(saleIngress.getItems()).isNotEmpty().hasSize(1);
    }
}