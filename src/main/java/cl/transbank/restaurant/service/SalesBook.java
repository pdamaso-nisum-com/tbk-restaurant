package cl.transbank.restaurant.service;

import cl.transbank.restaurant.domain.input.SaleIngress;
import cl.transbank.restaurant.messaging.SalesListener;
import cl.transbank.restaurant.messaging.SalesPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class SalesBook {

    private final SalesPublisher salesPublisher;
    private final SalesListener salesListener;

    public SalesBook(SalesPublisher salesPublisher,
                     SalesListener salesListener) {
        this.salesPublisher = salesPublisher;
        this.salesListener = salesListener;
    }

    public SaleIngress addEntry(SaleIngress saleIngress) {
        salesPublisher.publish(saleIngress);
        return saleIngress;
    }

    public Collection<SaleIngress> getEntriesBy(LocalDate date) {
        return salesListener.getAllByDate(date);
    }
}
