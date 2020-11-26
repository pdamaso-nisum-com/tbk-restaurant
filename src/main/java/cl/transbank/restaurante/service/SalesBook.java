package cl.transbank.restaurante.service;

import cl.transbank.restaurante.domain.SalesIngress;
import cl.transbank.restaurante.messaging.SalesIngressListener;
import cl.transbank.restaurante.messaging.SalesIngressPublisher;
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
