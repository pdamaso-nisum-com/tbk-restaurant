package cl.transbank.restaurant.controller;

import cl.transbank.restaurant.domain.SalesIngress;
import cl.transbank.restaurant.service.SalesBook;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/v1/sales")
public class SalesController {

    private final SalesBook salesBook;

    public SalesController(SalesBook salesBook) {
        this.salesBook = salesBook;
    }

    @PostMapping
    @ApiOperation(value = "Register a new sales ingress",
            response = SalesIngress.class)
    public ResponseEntity<SalesIngress> register(@RequestBody SalesIngress salesIngress) {
        SalesIngress salesEntry = salesBook.addEntry(salesIngress);
        return ResponseEntity.ok(salesEntry);
    }

    @GetMapping(path = "/{year}/{month}/{day}")
    @ApiOperation(value = "Get all sales by specified date",
            response = SalesIngress.class, responseContainer = "List")
    public ResponseEntity<Collection<SalesIngress>> getAll(@PathVariable Integer year,
                                                           @PathVariable Integer month,
                                                           @PathVariable Integer day) {
        LocalDate queryDate = LocalDate.of(year, month, day);
        Collection<SalesIngress> allSalesEntriesByDate = salesBook.getEntriesBy(queryDate);
        return ResponseEntity.ok(allSalesEntriesByDate);
    }
}
