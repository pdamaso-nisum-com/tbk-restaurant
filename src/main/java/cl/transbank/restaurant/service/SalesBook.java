package cl.transbank.restaurant.service;

import cl.transbank.restaurant.domain.SalesIngress;
import cl.transbank.restaurant.messaging.SalesIngressListener;
import cl.transbank.restaurant.messaging.SalesIngressPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class SalesBook {

    private final SalesIngressPublisher salesIngressPublisher;
    private final SalesIngressListener salesIngressListener;

    public SalesBook(SalesIngressPublisher salesIngressPublisher,
                     SalesIngressListener salesIngressListener) {
        this.salesIngressPublisher = salesIngressPublisher;
        this.salesIngressListener = salesIngressListener;
    }

    public SalesIngress addEntry(SalesIngress salesIngress) {
        salesIngressPublisher.publish(salesIngress);
        return salesIngress;
    }

    public Collection<SalesIngress> getEntriesBy(LocalDate date) {
        return salesIngressListener.getAllByDate(date);
    }
}
