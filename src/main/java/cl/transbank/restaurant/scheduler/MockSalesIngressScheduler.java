package cl.transbank.restaurant.scheduler;

import cl.transbank.restaurant.domain.SalesIngress;
import cl.transbank.restaurant.messaging.SalesIngressPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Slf4j
@Component
public class MockSalesIngressScheduler {

    private final SalesIngressPublisher salesIngressPublisher;

    public MockSalesIngressScheduler(SalesIngressPublisher salesIngressPublisher) {
        this.salesIngressPublisher = salesIngressPublisher;
    }

    @Scheduled(initialDelay = 30_000L, fixedDelay = 10_000L)
    public void trigger() {
        SalesIngress mockSalesIngress = getMockSalesIngress();
        salesIngressPublisher.publish(mockSalesIngress);
    }

    private SalesIngress getMockSalesIngress() {
        BigDecimal amount = BigDecimal.valueOf(RandomUtils.nextDouble(500.00, 20000.00))
                .setScale(2, RoundingMode.HALF_DOWN);
        SalesIngress salesIngress = SalesIngress.builder()
                .commerce(RandomUtils.nextLong(100, 199))
                .date(LocalDate.now())
                .terminal(RandomUtils.nextLong(500, 599))
                .amount(amount)
                .build();
        log.info("mockScheduler, salesIngress={}", salesIngress);
        return salesIngress;
    }
}
