package cl.transbank.restaurante.service;

import cl.transbank.restaurante.domain.SalesIngress;
import cl.transbank.restaurante.repository.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesBookTest {

    @Autowired
    private SalesBook salesBook;

    @Autowired
    private SalesRepository salesRepository;

    @BeforeEach
    void setUp() {
        salesRepository.deleteAll();
    }

    @Test
    void shouldAddEntry() {

        SalesIngress salesIngress = getSalesIngress(LocalDate.now());

        SalesIngress entry = salesBook.addEntry(salesIngress);

        assertThat(entry).isEqualTo(salesIngress);
        assertThat(salesRepository.findAll()).hasSize(1);
    }

    @Test
    void shouldGetEntriesByDate() {

        LocalDate today = LocalDate.now();

        SalesIngress yesterdaySale = getSalesIngress(today.minusDays(1));
        salesBook.addEntry(yesterdaySale);

        SalesIngress todaySale = getSalesIngress(today);
        salesBook.addEntry(todaySale);

        Collection<SalesIngress> todaySales = salesBook.getEntriesBy(today);

        assertThat(todaySales).containsOnly(todaySale);
        assertThat(salesRepository.findAll()).hasSize(2);
    }

    @Test
    void shouldGetEmptyEntriesWhenUnmatchedDate() {

        LocalDate today = LocalDate.now();

        Collection<SalesIngress> todaySales = salesBook.getEntriesBy(today);

        assertThat(todaySales).isEmpty();
        assertThat(salesRepository.findAll()).isEmpty();
    }

    private SalesIngress getSalesIngress(LocalDate date) {
        return SalesIngress.builder()
                .commerce(123L)
                .date(date)
                .terminal(456L)
                .amount(BigDecimal.valueOf(11.22))
                .build();
    }
}