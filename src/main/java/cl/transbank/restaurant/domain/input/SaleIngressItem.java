package cl.transbank.restaurant.domain.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleIngressItem implements Serializable {
    @ApiModelProperty(dataType = "string", value = "Product description", example = "coffee")
    private String product;
    @ApiModelProperty(dataType = "number", value = "Product price", example = "3271.605")
    private BigDecimal price;
    @ApiModelProperty(dataType = "int", value = "Product quantity", example = "2")
    private Integer quantity;
    @ApiModelProperty(dataType = "number", value = "Product total amount", example = "6543.21")
    private BigDecimal total;
}
