package cl.transbank.restaurante.scheduler;

import cl.transbank.restaurante.domain.SalesIngress;
import cl.transbank.restaurante.service.SalesBook;
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

    private final SalesBook salesBook;

    public MockSalesIngressScheduler(SalesBook salesBook) {
        this.salesBook = salesBook;
    }

    @Scheduled(initialDelay = 10_000L, fixedDelay = 10_000L)
    public void trigger() {
        SalesIngress mockSalesIngress = getMockSalesIngress();
        salesBook.addEntry(mockSalesIngress);
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
