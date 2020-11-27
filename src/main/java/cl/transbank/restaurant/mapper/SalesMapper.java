package cl.transbank.restaurant.mapper;

import cl.transbank.restaurant.domain.input.SaleIngress;
import cl.transbank.restaurant.domain.input.SaleIngressItem;
import cl.transbank.restaurant.domain.model.SaleEntity;
import cl.transbank.restaurant.domain.model.SaleItemEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SalesMapper {

    public SaleEntity map(SaleIngress saleIngress) {
        List<SaleIngressItem> items = Optional.ofNullable(saleIngress.getItems())
                .orElse(Collections.emptyList());
        List<SaleItemEntity> saleItemEntities = items.stream()
                .map(this::mapItem)
                .collect(Collectors.toList());
        return SaleEntity.builder()
                .commerce(saleIngress.getCommerce())
                .date(saleIngress.getDate())
                .terminal(saleIngress.getTerminal())
                .amount(saleIngress.getAmount())
                .items(saleItemEntities)
                .build();
    }

    private SaleItemEntity mapItem(SaleIngressItem saleIngressItem) {
        return SaleItemEntity.builder()
                .product(saleIngressItem.getProduct())
                .price(saleIngressItem.getPrice())
                .quantity(saleIngressItem.getQuantity())
                .total(saleIngressItem.getTotal())
                .build();
    }

    public SaleIngress map(SaleEntity sales) {
        List<SaleItemEntity> items = Optional.ofNullable(sales.getItems())
                .orElse(Collections.emptyList());
        List<SaleIngressItem> saleIngressItems = items.stream()
                .map(this::mapItem)
                .collect(Collectors.toList());
        return SaleIngress.builder()
                .commerce(sales.getCommerce())
                .date(sales.getDate())
                .terminal(sales.getTerminal())
                .amount(sales.getAmount())
                .items(saleIngressItems)
                .build();
    }

    private SaleIngressItem mapItem(SaleItemEntity saleItemEntity) {
        return SaleIngressItem.builder()
                .product(saleItemEntity.getProduct())
                .price(saleItemEntity.getPrice())
                .quantity(saleItemEntity.getQuantity())
                .total(saleItemEntity.getTotal())
                .build();
    }
}
