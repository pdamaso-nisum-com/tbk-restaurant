package cl.transbank.restaurant.messaging;

import cl.transbank.restaurant.domain.SalesIngress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesIngressPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange salesIngressExchange;

    public SalesIngressPublisher(RabbitTemplate rabbitTemplate,
                                 TopicExchange salesIngressExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.salesIngressExchange = salesIngressExchange;
    }

    public void publish(SalesIngress salesIngress) {
        rabbitTemplate.convertAndSend(salesIngressExchange.getName(), "sales-ingress-rk", salesIngress);
        log.info("messagePublished, salesIngress={}", salesIngress);
    }
}
