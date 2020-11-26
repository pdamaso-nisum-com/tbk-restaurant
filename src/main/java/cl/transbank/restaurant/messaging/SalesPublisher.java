package cl.transbank.restaurant.messaging;

import cl.transbank.restaurant.domain.SaleIngress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange salesIngressExchange;

    public SalesPublisher(RabbitTemplate rabbitTemplate,
                          TopicExchange salesIngressExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.salesIngressExchange = salesIngressExchange;
    }

    public void publish(SaleIngress saleIngress) {
        rabbitTemplate.convertAndSend(salesIngressExchange.getName(), "sales-ingress-rk", saleIngress);
        log.info("messagePublished, salesIngress={}", saleIngress);
    }
}
