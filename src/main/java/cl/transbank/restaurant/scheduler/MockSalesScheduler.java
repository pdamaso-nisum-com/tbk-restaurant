package cl.transbank.restaurant.scheduler;

import cl.transbank.restaurant.domain.SaleIngress;
import cl.transbank.restaurant.messaging.SalesPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Slf4j
@Component
public class MockSalesScheduler {

    private final SalesPublisher salesPublisher;

    public MockSalesScheduler(SalesPublisher salesPublisher) {
        this.salesPublisher = salesPublisher;
    }

    @Scheduled(initialDelay = 30_000L, fixedDelay = 10_000L)
    public void trigger() {
        SaleIngress mockSaleIngress = getMockSalesIngress();
        salesPublisher.publish(mockSaleIngress);
    }

    private SaleIngress getMockSalesIngress() {
        BigDecimal amount = BigDecimal.valueOf(RandomUtils.nextDouble(500.00, 20000.00))
                .setScale(2, RoundingMode.HALF_DOWN);
        SaleIngress saleIngress = SaleIngress.builder()
                .commerce(RandomUtils.nextLong(100, 199))
                .date(LocalDate.now())
                .terminal(RandomUtils.nextLong(500, 599))
                .amount(amount)
                .build();
        log.info("mockScheduler, salesIngress={}", saleIngress);
        return saleIngress;
    }
}
