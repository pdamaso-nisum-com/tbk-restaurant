package cl.transbank.restaurant.mapper;

import cl.transbank.restaurant.domain.model.SaleEntity;
import cl.transbank.restaurant.domain.SaleIngress;
import org.springframework.stereotype.Component;

@Component
public class SalesMapper {

    public SaleEntity map(SaleIngress saleIngress) {
        return SaleEntity.builder()
                .commerce(saleIngress.getCommerce())
                .date(saleIngress.getDate())
                .terminal(saleIngress.getTerminal())
                .amount(saleIngress.getAmount())
                .build();
    }

    public SaleIngress map(SaleEntity sales) {
        return SaleIngress.builder()
                .commerce(sales.getCommerce())
                .date(sales.getDate())
                .terminal(sales.getTerminal())
                .amount(sales.getAmount())
                .build();
    }
}
