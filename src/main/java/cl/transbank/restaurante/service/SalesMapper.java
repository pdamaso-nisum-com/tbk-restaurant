package cl.transbank.restaurante.service;

import cl.transbank.restaurante.domain.Sales;
import cl.transbank.restaurante.domain.SalesIngress;
import org.springframework.stereotype.Component;

@Component
public class SalesMapper {

    public Sales map(SalesIngress salesIngress) {
        return Sales.builder()
                .commerce(salesIngress.getCommerce())
                .date(salesIngress.getDate())
                .terminal(salesIngress.getTerminal())
                .amount(salesIngress.getAmount())
                .build();
    }

    public SalesIngress map(Sales sales) {
        return SalesIngress.builder()
                .commerce(sales.getCommerce())
                .date(sales.getDate())
                .terminal(sales.getTerminal())
                .amount(sales.getAmount())
                .build();
    }
}
