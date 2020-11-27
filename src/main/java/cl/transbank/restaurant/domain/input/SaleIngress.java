package cl.transbank.restaurant.domain.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleIngress implements Serializable {
    @ApiModelProperty(dataType = "long", value = "Commerce Id", example = "123456")
    private Long commerce;
    @ApiModelProperty(dataType = "string", value = "Sale date", example = "27-11-2020")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @ApiModelProperty(dataType = "long", value = "Terminal Id", example = "123456")
    private Long terminal;
    @ApiModelProperty(dataType = "number", value = "Sale total amount", example = "6543.21")
    private BigDecimal amount;
    @ApiModelProperty(value = "Sale items details", reference = "SaleIngressItem")
    private List<SaleIngressItem> items;
}