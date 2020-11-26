package cl.transbank.restaurante.messaging;

import cl.transbank.restaurante.domain.SalesIngress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesIngressListener {

    @RabbitListener(queues = "#{salesIngressQueue}")
    public void listen(@Payload SalesIngress salesIngress) {
        log.info("messageReceived, salesIngress={}", salesIngress);
    }

}
