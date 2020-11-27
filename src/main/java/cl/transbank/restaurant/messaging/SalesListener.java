package cl.transbank.restaurant.messaging;

import cl.transbank.restaurant.domain.model.SaleEntity;
import cl.transbank.restaurant.domain.input.SaleIngress;
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
public class SalesListener {

    private final SalesMapper salesMapper;
    private final SalesRepository salesRepository;
    private final Map<LocalDate, Collection<SaleIngress>> salesByDateMap;

    public SalesListener(SalesMapper salesMapper, SalesRepository salesRepository) {
        this.salesMapper = salesMapper;
        this.salesRepository = salesRepository;
        this.salesByDateMap = new HashMap<>();
    }

    @RabbitListener(queues = "#{salesIngressQueue}")
    private void listen(@Payload SaleIngress saleIngress) {
        log.info("messageReceived, salesIngress={}", saleIngress);
        SaleEntity sales = salesMapper.map(saleIngress);
        salesRepository.save(sales);
        log.info("messagePersisted, sales={}", sales);
        cacheSalesIngress(saleIngress);
    }

    private void cacheSalesIngress(SaleIngress saleIngress) {
        LocalDate ingressDate = saleIngress.getDate();
        Collection<SaleIngress> salesByDate = salesByDateMap.getOrDefault(ingressDate,
                Collections.synchronizedList(new ArrayList<>()));
        salesByDate.add(saleIngress);
        salesByDateMap.put(ingressDate, salesByDate);
    }

    public Collection<SaleIngress> getAllByDate(LocalDate date) {
        return salesByDateMap.getOrDefault(date, Collections.emptyList());
    }
}
