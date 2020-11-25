package cl.transbank.restaurante.repository;

import cl.transbank.restaurante.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    Collection<Sales> findAllByDateEquals(LocalDate date);
}
