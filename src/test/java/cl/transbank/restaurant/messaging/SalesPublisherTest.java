package cl.transbank.restaurant.messaging;

import cl.transbank.restaurant.domain.SaleIngress;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SalesPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private TopicExchange salesIngressExchange;

    @InjectMocks
    private SalesPublisher salesPublisher;

    @Test
    void shouldPublishSalesIngress() {

        SaleIngress saleIngress = SaleIngress.builder().build();

        given(salesIngressExchange.getName())
                .willReturn("mock-exchange");

        salesPublisher.publish(saleIngress);

        then(rabbitTemplate).should()
                .convertAndSend("mock-exchange", "sales-ingress-rk", saleIngress);
    }
}