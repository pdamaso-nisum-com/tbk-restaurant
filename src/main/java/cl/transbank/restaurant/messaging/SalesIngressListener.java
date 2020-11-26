package cl.transbank.restaurant.messaging;

import cl.transbank.restaurant.domain.Sales;
import cl.transbank.restaurant.domain.SalesIngress;
import cl.transbank.restaurant.mapper.SalesMapper;
import cl.transbank.restaurant.repository.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SalesIngressListener {

    private final SalesMapper salesMapper;
    private final SalesRepository salesRepository;
    private final Map<LocalDate, Collection<SalesIngress>> salesByDateMap;

    public SalesIngressListener(SalesMapper salesMapper, SalesRepository salesRepository) {
        this.salesMapper = salesMapper;
        this.salesRepository = salesRepository;
        this.salesByDateMap = new HashMap<>();
    }

    @RabbitListener(queues = "#{salesIngressQueue}")
    private void listen(@Payload SalesIngress salesIngress) {
        log.info("messageReceived, salesIngress={}", salesIngress);
        Sales sales = salesMapper.map(salesIngress);
        salesRepository.save(sales);
        log.info("messagePersisted, sales={}", sales);
        cacheSalesIngress(salesIngress);
    }

    private void cacheSalesIngress(SalesIngress salesIngress) {
        LocalDate ingressDate = salesIngress.getDate();
        Collection<SalesIngress> salesByDate = salesByDateMap.getOrDefault(ingressDate,
                Collections.synchronizedList(new ArrayList<>()));
        salesByDate.add(salesIngress);
        salesByDateMap.put(ingressDate, salesByDate);
    }

    public Collection<SalesIngress> getAllByDate(LocalDate date) {
        return salesByDateMap.getOrDefault(date, Collections.emptyList());
    }
}
