package cl.transbank.restaurante.controller;

import cl.transbank.restaurante.domain.SalesIngress;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/sales")
public class SalesController {

    private final List<SalesIngress> salesIngresses;

    public SalesController(List<SalesIngress> salesRegistry) {
        this.salesIngresses = salesRegistry;
    }

    @PostMapping
    public ResponseEntity<SalesIngress> register(@RequestBody SalesIngress salesIngress) {
        salesIngresses.add(salesIngress);
        return ResponseEntity.ok(salesIngress);
    }

    @GetMapping(path = "/{year}/{month}/{day}")
    public ResponseEntity<List<SalesIngress>> getAll(@PathVariable Integer year,
                                                     @PathVariable Integer month,
                                                     @PathVariable Integer day) {
        LocalDate queryDate = LocalDate.of(year, month, day);
        List<SalesIngress> salesOnSpecificDate = salesIngresses.stream()
                .filter(salesIngress -> salesIngress.getDate().isEqual(queryDate))
                .collect(Collectors.toList());
        return ResponseEntity.ok(salesOnSpecificDate);
    }
}
