package cl.transbank.restaurant.controller;

import cl.transbank.restaurant.domain.input.SaleIngress;
import cl.transbank.restaurant.service.SalesBook;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
            response = SaleIngress.class)
    public ResponseEntity<SaleIngress> register(@Valid @RequestBody SaleIngress saleIngress) {
        SaleIngress salesEntry = salesBook.addEntry(saleIngress);
        return ResponseEntity.ok(salesEntry);
    }

    @GetMapping(path = "/{year}/{month}/{day}")
    @ApiOperation(value = "Get all sales by specified date",
            response = SaleIngress.class, responseContainer = "List")
    public ResponseEntity<Collection<SaleIngress>> getAll(@PathVariable Integer year,
                                                          @PathVariable Integer month,
                                                          @PathVariable Integer day) {
        LocalDate queryDate = LocalDate.of(year, month, day);
        Collection<SaleIngress> allSalesEntriesByDate = salesBook.getEntriesBy(queryDate);
        return ResponseEntity.ok(allSalesEntriesByDate);
    }
}
