package cl.transbank.restaurante.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesIngress implements Serializable {
    @ApiModelProperty(dataType = "long", value = "Commerce Id")
    private Long commerce;
    @ApiModelProperty(dataType = "string", value = "Sale date", example = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @ApiModelProperty(dataType = "long", value = "Terminal Id")
    private Long terminal;
    @ApiModelProperty(dataType = "number", value = "Sale total amount")
    private BigDecimal amount;
}