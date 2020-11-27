package cl.transbank.restaurant.scheduler;

import cl.transbank.restaurant.domain.input.SaleIngress;
import cl.transbank.restaurant.domain.input.SaleIngressItem;
import cl.transbank.restaurant.messaging.SalesPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
        BigDecimal amount = BigDecimal.valueOf(RandomUtils.nextDouble(1000.00, 2000.00))
                .setScale(2, RoundingMode.HALF_DOWN);
        SaleIngressItem saleIngressItem = SaleIngressItem.builder()
                .product("coffee")
                .price(amount)
                .quantity(1)
                .total(amount)
                .build();
        List<SaleIngressItem> items = Collections.singletonList(saleIngressItem);
        SaleIngress saleIngress = SaleIngress.builder()
                .commerce(RandomUtils.nextLong(1000, 1999))
                .date(LocalDate.now())
                .terminal(RandomUtils.nextLong(5000, 5999))
                .amount(amount)
                .items(items)
                .build();
        log.info("mockScheduler, salesIngress={}", saleIngress);
        return saleIngress;
    }
}
