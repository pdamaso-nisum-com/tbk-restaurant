package cl.transbank.restaurant.repository;

import cl.transbank.restaurant.domain.model.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface SalesRepository extends JpaRepository<SaleEntity, Long> {
    Collection<SaleEntity> findAllByDateEquals(LocalDate date);
}
