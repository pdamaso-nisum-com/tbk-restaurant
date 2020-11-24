package cl.transbank.restaurante.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesIngress {
    private Long commerce;
    private LocalDate date;
    private Long terminal;
    private BigDecimal amount;
}