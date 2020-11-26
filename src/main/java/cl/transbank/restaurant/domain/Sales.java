package cl.transbank.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long commerce;
    private LocalDate date;
    private Long terminal;
    private BigDecimal amount;
}