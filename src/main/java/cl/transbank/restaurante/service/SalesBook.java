package cl.transbank.restaurante.service;

import cl.transbank.restaurante.domain.Sales;
import cl.transbank.restaurante.domain.SalesIngress;
import cl.transbank.restaurante.repository.SalesRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class SalesBook {

    private final SalesMapper salesMapper;
    private final SalesRepository salesRepository;

    public SalesBook(SalesMapper salesMapper,
                     SalesRepository salesRepository) {
        this.salesMapper = salesMapper;
        this.salesRepository = salesRepository;
    }

    public SalesIngress addEntry(SalesIngress salesIngress) {
        Sales sales = salesMapper.map(salesIngress);
        salesRepository.save(sales);
        return salesIngress;
    }

    public Collection<SalesIngress> getEntriesBy(LocalDate date) {
        Collection<Sales> allByDateEquals = salesRepository.findAllByDateEquals(date);
        return allByDateEquals.stream()
                .map(salesMapper::map)
                .collect(Collectors.toList());
    }
}
