package cl.transbank.restaurant.repository;

import cl.transbank.restaurant.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    Collection<Sales> findAllByDateEquals(LocalDate date);
}
